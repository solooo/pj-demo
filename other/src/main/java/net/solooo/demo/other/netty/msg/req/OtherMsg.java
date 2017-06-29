package net.solooo.demo.other.netty.msg.req;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class OtherMsg {
    /**
     * posID :
     * fileName :
     * fileLength :
     * fileStartTime : 0
     * fileStopTime : 0
     * frameIndexFileData :
     */

    private String posID;

    private String fileName;

    private String fileLength;

    private int fileStartTime;

    private int fileStopTime;

    private String frameIndexFileData;

    public String getPosID() {
        return posID;
    }

    public void setPosID(String posID) {
        this.posID = posID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLength() {
        return fileLength;
    }

    public void setFileLength(String fileLength) {
        this.fileLength = fileLength;
    }

    public int getFileStartTime() {
        return fileStartTime;
    }

    public void setFileStartTime(int fileStartTime) {
        this.fileStartTime = fileStartTime;
    }

    public int getFileStopTime() {
        return fileStopTime;
    }

    public void setFileStopTime(int fileStopTime) {
        this.fileStopTime = fileStopTime;
    }

    public String getFrameIndexFileData() {
        return frameIndexFileData;
    }

    public void setFrameIndexFileData(String frameIndexFileData) {
        this.frameIndexFileData = frameIndexFileData;
    }
}
