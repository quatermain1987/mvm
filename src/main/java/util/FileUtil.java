package util;

import java.io.File;

public class FileUtil {
	
	public static final String UPLOAD_PATH="/Users/p.jina/java/Movivum/src/main/webapp/upload/img";
	
	public static void removeFile(String filename) {
		File file=new File(UPLOAD_PATH,filename);//1.경로,2.파일명
		if(file.exists()) file.delete();
	}
}
