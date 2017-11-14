package com.devsun.eternal.test.mxd2;

import java.util.Random;

import com.devsun.eternal.common.mxd2.macro.KeyDirection;
import com.devsun.eternal.common.mxd2.macro.KeyValue;
import com.devsun.eternal.common.mxd2.macro.MoveDirection;

public class MacroBaseTest {
	
	/**
	 * 移动 <br/>
	 * 1 2 3 <br/>
	 * 4 + 5 <br/>
	 * 6 7 8 <br/>
	 * @param type 方向 1-8
	 * @return
	 */
	protected String move(MoveDirection moveDirection, int time, int min, int max){
		move(moveDirection, KeyDirection.DOWN);
		int ms = time;
		if((min>0 && min<time) && (max>0 && max>time)){
			ms= random(min, max);
		}
		//移动
		delay(ms);
		move(moveDirection, KeyDirection.UP);
		//补全
		if(ms<time){
			move(moveDirection, KeyDirection.DOWN);
			delay(ms-time);
			move(moveDirection, KeyDirection.UP);
		}
		else if(ms<time){
			back(moveDirection, KeyDirection.DOWN);
			delay(time-ms);
			back(moveDirection, KeyDirection.UP);
		}
		return "";
	}
	
	/**
	 * 移动
	 * @param moveDirection 方向
	 * @param keyDirection 按下,放开
	 * @return
	 */
	private String move(MoveDirection moveDirection, KeyDirection keyDirection){
		StringBuffer stringBuffer = new StringBuffer();
		switch(moveDirection){
			case UPLEFT: 	stringBuffer.append(keyUp(keyDirection)).append(keyLeft(keyDirection));
			case UP: 		stringBuffer.append(keyUp(keyDirection));
			case UPRIGHT: 	stringBuffer.append(keyUp(keyDirection)).append(keyRight(keyDirection));
			case LEFT: 		stringBuffer.append(keyLeft(keyDirection));
			case RIGHT: 	stringBuffer.append(keyRight(keyDirection));
			case DOWNLEFT: 	stringBuffer.append(keyDown(keyDirection)).append(keyLeft(keyDirection));
			case DOWN: 		stringBuffer.append(keyDown(keyDirection));
			case DOWNRIGHT: stringBuffer.append(keyDown(keyDirection)).append(keyRight(keyDirection));
			default: ;
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 反向移动
	 * @param type 方向
	 * @param direction 按下,放开
	 * @return
	 */
	private String back(MoveDirection moveDirection, KeyDirection direction){
		MoveDirection back = null;
		switch(moveDirection){
			case UPLEFT: 	back = MoveDirection.DOWNRIGHT;
			case UP: 		back = MoveDirection.DOWN;
			case UPRIGHT: 	back = MoveDirection.DOWNLEFT;
			case LEFT: 		back = MoveDirection.RIGHT;
			case RIGHT: 	back = MoveDirection.LEFT;
			case DOWNLEFT: 	back = MoveDirection.UPRIGHT;
			case DOWN: 		back = MoveDirection.UP;
			case DOWNRIGHT: back = MoveDirection.UPLEFT;
			default: ;
		}
		return move(back, direction);
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
	
	protected String keyUp(KeyDirection direction){
		return key(direction, KeyValue.UP);
	}
	
	protected String keyDown(KeyDirection direction){
		return key(direction, KeyValue.DOWN);
	}
	
	protected String keyLeft(KeyDirection direction){
		return key(direction, KeyValue.LEFT);
	}
	
	protected String keyRight(KeyDirection direction){
		return key(direction, KeyValue.RIGHT);
	}
	
	protected String keySpacebar(KeyDirection direction){
		return key(direction, KeyValue.SPACEBAR);
	}
	
	protected String key(KeyDirection direction, KeyValue value){
		return "\n<key direction=\""+direction.getText()+"\" value=\""+value.getText()+"\"/>";
	}
	
	protected String delay(int ms){
		return "\n<delay milliseconds=\""+ms+"\"/>";
	}
	
}
