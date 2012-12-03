#include "phonet_CPhonet.h"
#include "phonet.h"
#include <string.h>
#include <iconv.h>

/**
 * Convert input UTF to CP1252. The phonet.c works on one-byte encoded 
 * characters. All the map characters to only fit into CP1252 not ISO-8859-1. 
 */
static int convert_cp1252(char input[], char output[]) {
	char *inbuf = &input[0];
	size_t inbytesleft = strlen(input);
	char *outbuf = &output[0];
	size_t outbytesleft = sizeof(output) - 1;
	
	iconv_t cd = iconv_open("CP1252", "UTF-8");
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
	if (input == NULL || convert_cp1252(input, output) == -1) {
		perror("input failed");
		return NULL;
	}
	
	(*env)->ReleaseStringUTFChars(env, prompt, input);
	
	int r = phonet(output, res, size, PHONET_FIRST_RULES + PHONET_GERMAN);
	if(r < 0) return NULL;
	return (*env)->NewStringUTF(env, res);
}

