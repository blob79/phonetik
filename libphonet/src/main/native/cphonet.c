#pragma GCC diagnostic ignored "-Wunused-parameter"
#include "phonet_CPhonet.h"
#include "ph_ext.h"
#include <string.h>
#include <iconv.h>

/**
 * Convert input UTF to CP1252. The phonet.c works on one-byte encoded
 * characters. All the characters fit only into CP1252 not ISO-8859-1.
 */
static int convert(char *input_enc, char input[], char *output_enc,
		char output[], size_t outbytesleft) {
	char *inbuf = &input[0];
	size_t inbytesleft = strlen(input);
	char *outbuf = &output[0];
	iconv_t cd = iconv_open(output_enc, input_enc);
	do {
		if (iconv(cd, &inbuf, &inbytesleft, &outbuf, &outbytesleft) == (size_t) -1) {
			iconv_close(cd);
			return -1;
		}
	} while (inbytesleft > 0 && outbytesleft > 0);
	*outbuf = 0;
	iconv_close(cd);
	return 0;
}


JNIEXPORT jstring JNICALL 
Java_phonet_CPhonet_phonet(JNIEnv *env, jobject obj, jstring prompt) {
	unsigned int size = 1<<10;
	char res[size];
	char output[size];
	char *input = (char *) (*env)->GetStringUTFChars(env, prompt, NULL);
	if (input == NULL || 
		convert("UTF-8", input, "CP1252", output, sizeof(output) - 1) == -1) {
		perror("encoding failed");
		return NULL;
	}
	
	(*env)->ReleaseStringUTFChars(env, prompt, input);
	
	int r = phonet(output, res, size, PHONET_FIRST_RULES + PHONET_GERMAN);
	if(r < 0) return NULL;
	if (convert("CP1252", res, "UTF-8", output, sizeof(output) - 1) == -1) {
		perror("encoding failed");
		return NULL;
	}
	//printf("C: %d %c %d %c \n", output[0], output[0], res[0], res[0]);
	//fflush(stdout);
	return (*env)->NewStringUTF(env, output);
}

