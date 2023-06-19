package com.example.board2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.board2.dto.*;

@Controller // 1. controller 역활 클래스 , 2. bean 객체가 된다.
@RequestMapping(value = "/thymeleaf") // 변수를 넘겨줄수있다. 2. thymeleafEx 경로를 만들어 준다. ex/ localhost/thymeleaf
public class thymeleafExController {

	@GetMapping(value = "/ex01") // getMapping 경로를 지정해준다 /ex01이 들어간 주소를 실행해줌
	public String thymeleafExample01(Model model) {

		// request.setAttribute (key , 매개변수) 값과 같은 느낌
		model.addAttribute("data", "타임리프 예제입니다."); // 데이터를 (key , value) 형태로 넘어간다.

		// return 폴더명/내가띄울화면의파일명;
		return "thymeleafEx/thymeleafEx01";
	}

	@GetMapping(value = "/ex02") // getMapping 경로를 지정해준다 /ex02이 들어간 주소를 실행해줌
	public String thymeleafExample02(Model model) {
		ItemDto itemDto = new ItemDto();

		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setItemNm("테스트 상품1");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());

		model.addAttribute("itemDto", itemDto);

		// return 폴더명/내가띄울화면의파일명;
		return "thymeleafEx/thymeleafEx02";
	}

	@GetMapping(value = "/ex03") // getMapping 경로를 지정해준다 /ex03이 들어간 주소를 실행해줌
	public String thymeleafExample03(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			ItemDto itemDto = new ItemDto();

			itemDto.setItemDetail("상품 상세 설명" + i);
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());

			itemDtoList.add(itemDto);
		}

		model.addAttribute("itemDtoList", itemDtoList);

		return "thymeleafEx/thymeleafEx03";

	}

	@GetMapping(value = "/ex04") // getMapping 경로를 지정해준다 /ex04이 들어간 주소를 실행해줌
	public String thymeleafExample04(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();

		// for문을 이용하여 상품의 여러가지 리스트를 출력한다. 
		for (int i = 1; i <= 10; i++) {
			ItemDto itemDto = new ItemDto();

			itemDto.setItemDetail("상품 상세 설명" + i);
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());

			itemDtoList.add(itemDto);
		}

		model.addAttribute("itemDtoList", itemDtoList);
		
		
		return "thymeleafEx/thymeleafEx04";
	}
	
	
	
	@GetMapping(value = "/ex05") // getMapping 경로를 지정해준다 /ex05이 들어간 주소를 실행해줌
	public String thymeleafExample05() {

		return "thymeleafEx/thymeleafEx05";
	}
	
	@GetMapping(value = "/ex06") // getMapping 경로를 지정해준다 /ex06이 들어간 주소를 실행해줌
	public String thymeleafExample06(Model model,  String param1 , String param2 ) {
		
		//파라메터에 저장된 값을 그대로 model을 통해 넘겨준다.
		model.addAttribute("param1" , param1);
		model.addAttribute("param2" , param2);
		
		System.out.println("param1" + param1);  //데이터1
		System.out.println("param2" + param2);  // 데이터2
		
		return "thymeleafEx/thymeleafEx06";
	}
	
	@GetMapping(value = "/ex07") // getMapping 경로를 지정해준다 /ex07이 들어간 주소를 실행해줌
	public String thymeleafExample07() {

		return "thymeleafEx/thymeleafEx07";
	}
}
