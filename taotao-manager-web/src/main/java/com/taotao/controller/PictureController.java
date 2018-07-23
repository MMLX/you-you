package com.taotao.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {
	@Autowired
	private PictureService pictureService; 

	@RequestMapping("/upload")
	@ResponseBody()
	public PictureResult uploda(MultipartFile uploadFile){
		try {
			//dubbo 不支持MultipartFile参数类型 如果要上传图片 必须变成byte数组类型的数据
			byte[] bytes = uploadFile.getBytes();
			//得到文件名称
			String name = uploadFile.getOriginalFilename();
			PictureResult pictureResult = pictureService.uploadFile(bytes,name);
			return pictureResult;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
