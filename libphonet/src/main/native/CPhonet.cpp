#include "phonet_CPhonet.h"
#include "phonet.h"
#include <string.h>

JNIEXPORT jstring JNICALL 
Java_phonet_CPhonet_phonet(JNIEnv *env, jobject obj, jstring prompt) {
	int size = 1<<10;
	char res[size];
	char buf[size];
    const char *str;
    
    str = env->GetStringUTFChars(prompt, NULL);
    if (str == NULL) {
         return NULL; 
     }
     strncpy(buf,str,size);
     int r = phonet(buf, res, size, PHONET_FIRST_RULES + PHONET_GERMAN);
     if(r < 0) return NULL;
     
     env->ReleaseStringUTFChars(prompt, str);
     return env->NewStringUTF(res);

}

