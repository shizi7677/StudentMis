package com.hust.xin.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 关闭流的工具类
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
