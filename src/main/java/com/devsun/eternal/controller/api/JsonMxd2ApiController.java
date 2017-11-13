package com.devsun.eternal.controller.api;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.model.mxd2.Oxanswer;
import com.devsun.eternal.service.mxd2.IOxanswerService;

@Controller
@RequestMapping("/api/mxd2")
public class JsonMxd2ApiController {
	
	@Autowired
	private IOxanswerService oxanswerService;
	
	private static final ThreadLocal<StringBuffer> MACRO = new ThreadLocal<>();
	
	//初始化移动范围
	private int MOVE_INIT_MIN = 200;
	private int MOVE_INIT_MAX = 600;
	private int MOVE_INIT_TIME = 400;
	
	//移动范围
	private int MOVE_MIN = 400;
	private int MOVE_MAX = 1000;
	private int MOVE_TIME = 800;
	
	//发愣范围
	private int AWAIT_MIN = 200;
	private int AWAIT_MAX = 1000;
	
	//采集按住时间
	private int ACTION_HOLD_MIN = 50;
	private int ACTION_HOLD_MAX = 500;
	
	//采集等待时间
	private int ACTION_WAIT_MIN = 3000;
	private int ACTION_WAIT_MAX = 4000;
	
	
	@RequestMapping(value = "/oxanswer/all.json", method = RequestMethod.GET)
	@ResponseBody
	public String oxanswerAll(){
		List<Oxanswer> list = oxanswerService.getAll();
		StringBuffer js = new StringBuffer();
		js.append("var tdQuestions={'xlinfo':{");
		int index = 0;
		for(Oxanswer oxanswer : list){
			js.append("'").append(index).append("'").append(":{'question':'").append(oxanswer.getTitle()).append("','opt1':'").append(oxanswer.getIsTrue()==1?"O":"X").append("'},");
			index++;
		}
		js.append("}}");
		return js.toString();
	}
	
	@RequestMapping(value = "/macro/all.json", method = RequestMethod.GET)
	@ResponseBody
	public void macroAll(HttpServletResponse response){
		List<Integer> list = new LinkedList<Integer>();
		list.add(3);//1
		list.add(7);//2
		list.add(12);//3
		list.add(16);//4
		list.add(21);//5
		list.add(25);//6
		list.add(30);//7
		list.add(36);//8
		list.add(41);//9
		list.add(48);//10
		list.add(59);//11
		list.add(0);//12
		list.add(0);//13
		StringBuffer stringBuffer = new StringBuffer("<macro name=\"采集\" hidden=\"false\" guid=\"{DE884125-DED1-446B-8C1B-C81ADF189425}\">");
		stringBuffer.append("\n<multikey xmlns=\"http://www.logitech.com/Cassandra/2010.1/Macros/MultiKey\">");
		MACRO.set(stringBuffer);
		for(int i=1;i<=list.size();i++){
			int times = list.get(i-1);
			if(times==0){
				break;
			}
			if(i==1){
				moveInit();
				action(times);
				moveRight();
				action(times);
			}
			//偶数
			else if(i%2==0){
				moveUp();
				action(times);
				moveLeft();
				action(times);
			}
			//奇数
			else{
				moveUp();
				action(times);
				moveRight();
				action(times);
			}
		}
		stringBuffer.append("\n</multikey>");
		stringBuffer.append("\n</macro>");
		
//		stringBuffer = new StringBuffer();
//		for(int i=0;i<987;i++) {
//			stringBuffer.append("\n<key direction=\"down\" value=\"W\"/>");
//			stringBuffer.append("\n<delay milliseconds=\""+random(50, 200)+"\"/>");
//			stringBuffer.append("\n<key direction=\"up\" value=\"W\"/>");
//			stringBuffer.append("\n<delay milliseconds=\""+random(3000, 4000)+"\"/>");
//		}
		
		try {
			response.reset();
			response.setContentType("text/plain;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化移动
	 */
	private void moveInit(){
		int ms = random(MOVE_INIT_MIN, MOVE_INIT_MAX);
		downUp();
		delay(ms);
		upUp();
		await();
		//补全
		if(ms>MOVE_INIT_TIME){
			downDown();
			delay(ms-MOVE_INIT_TIME);
			upDown();
		}
		else{
			downUp();
			delay(MOVE_INIT_TIME-ms);
			upUp();
		}
		await();
	}
	
	/**
	 * 向上走
	 */
	private void moveUp(){
		int ms = random(MOVE_MIN, MOVE_MAX);
		downUp();
		downLeft();
		delay(ms);
		upLeft();
		upUp();
		await();
		//补全
		if(ms>MOVE_TIME){
			downRight();
			downDown();
			delay(ms-MOVE_TIME);
			upRight();
			upDown();
		}
		else{
			downUp();
			downLeft();
			delay(MOVE_TIME-ms);
			upLeft();
			upUp();
		}
	}
	
	/**
	 * 向左走
	 */
	private void moveLeft(){
		int ms = random(MOVE_MIN, MOVE_MAX);
		downLeft();
		downDown();
		delay(ms);
		upLeft();
		upDown();
		await();
		//补全
		if(ms>MOVE_TIME){
			downRight();
			downUp();
			delay(ms-MOVE_TIME);
			upRight();
			upUp();
		}
		else{
			downLeft();
			downDown();
			delay(MOVE_TIME-ms);
			upLeft();
			upDown();
		}
	}
	
	/**
	 * 向右走
	 */
	private void moveRight(){
		int ms = random(MOVE_MIN, MOVE_MAX);
		downUp();
		downRight();
		delay(ms);
		upRight();
		upUp();
		await();
		//补全
		if(ms>MOVE_TIME){
			downLeft();
			downDown();
			delay(ms-MOVE_TIME);
			upLeft();
			upDown();
		}
		else{
			downUp();
			downRight();
			delay(MOVE_TIME-ms);
			upRight();
			upUp();
		}
	}
	
	/**
	 * 执行
	 * @param times
	 */
	private void action(int times){
		for(int i=0;i<times;i++) {
			downSpacebar();
			delay(random(ACTION_HOLD_MIN, ACTION_HOLD_MAX));
			upSpacebar();
			delay(random(ACTION_WAIT_MIN, ACTION_WAIT_MAX));//等待采集cd
		}
	}
	
	/**
	 * 发愣
	 */
	private void await(){
		delay(random(AWAIT_MIN, AWAIT_MAX));
	}
	
	/**
	 * 随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min, int max){
		Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
	}
	
	/********************************原始代码************************************/
	
	private void downUp(){
		MACRO.get().append("\n<key direction=\"down\" value=\"UP\"/>");
	}
	
	private void downDown(){
		MACRO.get().append("\n<key direction=\"down\" value=\"DOWN\"/>");
	}
	
	private void downLeft(){
		MACRO.get().append("\n<key direction=\"down\" value=\"LEFT\"/>");
	}
	
	private void downRight(){
		MACRO.get().append("\n<key direction=\"down\" value=\"RIGHT\"/>");
	}
	
	private void downSpacebar(){
		MACRO.get().append("\n<key direction=\"down\" value=\"SPACEBAR\"/>");
	}
	
	private void upUp(){
		MACRO.get().append("\n<key direction=\"up\" value=\"UP\"/>");
	}
	
	private void upDown(){
		MACRO.get().append("\n<key direction=\"up\" value=\"DOWN\"/>");
	}
	
	private void upLeft(){
		MACRO.get().append("\n<key direction=\"up\" value=\"LEFT\"/>");
	}
	
	private void upRight(){
		MACRO.get().append("\n<key direction=\"up\" value=\"RIGHT\"/>");
	}
	
	private void upSpacebar(){
		MACRO.get().append("\n<key direction=\"up\" value=\"SPACEBAR\"/>");
	}
	
	private void delay(int ms){
		MACRO.get().append("\n<delay milliseconds=\""+ms+"\"/>");
	}

}
