package com.example.test.comm.base;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaseThread extends Thread{

//	private static Logger log = Logger.getLogger(BaseThread.class);
	private static Logger log = LoggerFactory.getLogger(BaseThread.class);

	public enum THREAD_PROC_RET{
		OK,	// 执行成功
		NG, // 执行失败
		NONE // 本轮无执行
	}

	public void init(String threadName, long startWaitingSecond){
		this.threadName = threadName;
		this.startWaitingSecond = startWaitingSecond;
	}

	public void init(String threadName, long startWaitingSecond, long loopTimerSecond, long totalLoopTimerSecond){
		this.threadName = threadName;
		this.startWaitingSecond = startWaitingSecond;
		this.sleepTimerSecond = loopTimerSecond;
		this.maxSleepTimerSecond = totalLoopTimerSecond;
	}

	/**
	 * 线程名称
	 */
	private String threadName = StringUtils.EMPTY;
	/**
	 * 初次启动线程是否Sleep
	 */
	private boolean firstRunFlag = true;
	/**
	 * 线程运行Flag
	 */
	private boolean runFlag = false;
	/**
	 * 线程是否立即运行Flag
	 */
	private boolean immediatelyRunFlag = false;
	/**
	 * 初次启动线程Sleep Timer [秒]
	 */
	private long startWaitingSecond = 30;
	/**
	 * 线程Sleep Timer [秒]
	 */
	private long sleepTimerSecond = 30;
	/**
	 * 线程核心方法执行结果
	 */
	private THREAD_PROC_RET mainProcRet = THREAD_PROC_RET.OK;
	/**
	 * 线程合计等待时间 [秒]
	 */
	private long totalSleepTimerSecond = 30;
	/**
	 * 线程剩余等待时间[秒]
	 */
	private long remainTimerSecond = 0;
	/**
	 * 线程合计等待时间自增量[秒]
	 */
	private long increaseTimerSecond = 30;
	/**
	 * 线程最大等待时间[秒]
	 */
	private long maxSleepTimerSecond = 1800;
	/**
	 * 线程最后一次运行结果
	 */
	private THREAD_PROC_RET lastProcRet = THREAD_PROC_RET.OK;

	public void run(){

		log.info("┌────────────────────────────【"+this.threadName+"】启动────────────────────────────┐");
		if(firstRunFlag){
			try{
				log.info("├【"+this.threadName+"】首次启动，Sleep ["+this.startWaitingSecond+"] 秒！");
				Thread.sleep(this.startWaitingSecond * 1000);
			}catch(Exception e){
				log.error("├【"+this.threadName+"】首次启动异常",e);
			}
			firstRunFlag = false;
		}

		while(runFlag){
			log.debug("┌────────────────────────────【"+this.threadName+"】本轮执行开始────────────────────────────┐");
			if(!this.immediatelyRunFlag){
				if(lastProcRet == THREAD_PROC_RET.NONE){
					// 如果上一次执行结果为无任务，则累加Timer
					remainTimerSecond = remainTimerSecond - sleepTimerSecond;
					if(remainTimerSecond <= 0){
						log.info("├【"+this.threadName+"】上轮任务执行空闲，累计等待时间已到，本轮正常执行！");
					}else{
						log.debug("├【"+this.threadName+"】累计等待时间未到，剩余["+remainTimerSecond+"]／["+totalSleepTimerSecond+"]秒，本轮继续等待！");
						try{
							log.debug("└────────────────────────────【"+this.threadName+"】本轮执行完成────────────────────────────┘");
							Thread.sleep(sleepTimerSecond * 1000);
						}catch(Exception e){
							log.error("├【"+this.threadName+"】本轮执行完成等待时异常",e);
						}
						continue;
					}
				}else if(lastProcRet == THREAD_PROC_RET.OK){
					log.info("├【"+this.threadName+"】上轮任务执行成功，本轮正常执行！");
				}else{
					log.info("├【"+this.threadName+"】上轮任务执行失败，本轮正常执行！");
				}
			}else{
				log.info("├【"+this.threadName+"】已被设定为立即执行！");
				this.immediatelyRunFlag = false;
			}

			log.info("├【"+this.threadName+"】开始执行线程主函数threadMainProc()");
			try{
				mainProcRet = threadMainProc();
				lastProcRet = mainProcRet;
				switch(mainProcRet){
					case OK:
						log.info("├─【"+this.threadName+"】执行线程主函数threadMainProc()执行成功！");
						log.debug("└────────────────────────────【"+this.threadName+"】终止────────────────────────────┘");
						break;
					case NG:
						log.warn("├─【"+this.threadName+"】执行线程主函数threadMainProc()执行失败！");
						log.debug("└────────────────────────────【"+this.threadName+"】终止────────────────────────────┘");
						break;
					case NONE:
						log.info("├─【"+this.threadName+"】执行线程主函数threadMainProc()无任务！");
						log.debug("└────────────────────────────【"+this.threadName+"】终止────────────────────────────┘");
						break;
				}
			}catch(Exception e){
				log.warn("├【"+this.threadName+"】执行线程主函数threadMainProc()执行异常！",e);
			}
			try{
				if(lastProcRet == THREAD_PROC_RET.NONE){
					log.info("├【"+this.threadName+"】执行线程主函数threadMainProc()无任务，累加空闲Timer！");
					totalSleepTimerSecond += increaseTimerSecond;
					if(totalSleepTimerSecond > maxSleepTimerSecond){
						totalSleepTimerSecond = maxSleepTimerSecond;
					}
					remainTimerSecond = totalSleepTimerSecond;
					log.info("├【"+this.threadName+"】下次执行时间["+totalSleepTimerSecond+"]秒后！");
				}else{
					totalSleepTimerSecond = 0;
				}
				log.debug("└────────────────────────────【"+this.threadName+"】本轮执行完成────────────────────────────┘");
				Thread.sleep(sleepTimerSecond * 1000);
			}catch(Exception e){ }

		}

		log.debug("└────────────────────────────【"+this.threadName+"】终止────────────────────────────┘");
	}

	protected THREAD_PROC_RET threadMainProc() throws Exception{
		return THREAD_PROC_RET.NONE;
	}

	protected String getThreadName() {
		return threadName;
	}

	protected void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}

	public void setImmediatelyRunFlag(boolean immediatelyRunFlag) {
		log.info("设置线程["+this.threadName+"]立即启动为["+immediatelyRunFlag+"]！");
		this.immediatelyRunFlag = immediatelyRunFlag;
	}

	protected void setStartWaitingSecond(long startWaitingSecond) {
		this.startWaitingSecond = startWaitingSecond;
	}

	protected boolean isFirstRunFlag() {
		return firstRunFlag;
	}

	protected void setFirstRunFlag(boolean firstRunFlag) {
		this.firstRunFlag = firstRunFlag;
	}

	protected long getSleepTimerSecond() {
		return sleepTimerSecond;
	}

	protected void setSleepTimerSecond(long sleepTimerSecond) {
		this.sleepTimerSecond = sleepTimerSecond;
	}

	protected THREAD_PROC_RET getMainProcRet() {
		return mainProcRet;
	}

	protected void setMainProcRet(THREAD_PROC_RET mainProcRet) {
		this.mainProcRet = mainProcRet;
	}

	protected long getTotalSleepTimerSecond() {
		return totalSleepTimerSecond;
	}

	protected void setTotalSleepTimerSecond(long totalSleepTimerSecond) {
		this.totalSleepTimerSecond = totalSleepTimerSecond;
	}

	protected long getRemainTimerSecond() {
		return remainTimerSecond;
	}

	protected void setRemainTimerSecond(long remainTimerSecond) {
		this.remainTimerSecond = remainTimerSecond;
	}

	protected long getIncreaseTimerSecond() {
		return increaseTimerSecond;
	}

	protected void setIncreaseTimerSecond(long increaseTimerSecond) {
		this.increaseTimerSecond = increaseTimerSecond;
	}

	protected long getMaxSleepTimerSecond() {
		return maxSleepTimerSecond;
	}

	protected void setMaxSleepTimerSecond(long maxSleepTimerSecond) {
		this.maxSleepTimerSecond = maxSleepTimerSecond;
	}

	protected THREAD_PROC_RET getLastProcRet() {
		return lastProcRet;
	}

	protected void setLastProcRet(THREAD_PROC_RET lastProcRet) {
		this.lastProcRet = lastProcRet;
	}

	protected boolean isRunFlag() {
		return runFlag;
	}

	protected boolean isImmediatelyRunFlag() {
		return immediatelyRunFlag;
	}

	public long getStartWaitingSecond() {
		return startWaitingSecond;
	}

	
	public static void main(String[] args) {
		long totalSleepTimerSecond = 1769;
		long increaseTimerSecond = 30;
		long maxSleepTimerSecond = 30 * 60;
		long remainTimerSecond = 0;
		totalSleepTimerSecond += increaseTimerSecond;
		if (totalSleepTimerSecond > maxSleepTimerSecond) {
			totalSleepTimerSecond = maxSleepTimerSecond;
		}
		remainTimerSecond = totalSleepTimerSecond;
		log.info("├下次执行时间[" + totalSleepTimerSecond+ "]秒后！");
	}
}


