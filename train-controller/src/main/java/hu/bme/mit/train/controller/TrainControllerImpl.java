package hu.bme.mit.train.controller;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Date;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;


	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public Table tachograph(){
		Table<Long,Integer, Integer> tacho = HashBasedTable.create();
		Date date = new Date();
		tacho.put(date.getTime(),this.step, referenceSpeed);
		return tacho;
	}

}
