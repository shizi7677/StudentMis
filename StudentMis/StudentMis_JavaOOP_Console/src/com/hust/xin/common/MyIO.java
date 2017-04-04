package com.hust.xin.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * �ر����Ĺ�����
 * @author ttc
 *
 */
public class MyIO {

	public static void closeInputStream(InputStream is){

		try {
			if(is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void closeOutputStream(OutputStream os){
		try {
			if(os != null) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
