package com.taotao.service;

import java.io.InputStream;


import com.taotao.common.pojo.PictureResult;

public interface PictureService {
	/**
	 * 上传图片，但是不写入 数据库
	 * @param bytes 需要上传的图片的字节数组
	 * @param name 图片的名称
	 * @return 上传成功返回{"error":0,"url":"图片地址"} 上传失败{"error":1,"message":"错误消息"}
	 */
	public PictureResult uploadFile(byte[] bytes, String name);
}
