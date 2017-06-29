package net.solooo.demo.other.netty.msg.req;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class DataMsg {

    /**
     * seq : 1
     * oid :
     * lastPacket : 0
     * mediaData :
     * other : {"posID":"","fileName":"","fileLength":"","fileStartTime":0,"fileStopTime":0,"frameIndexFileData":""}
     */

    private int seq;

    private String oid;

    private int lastPacket;

    private String mediaData;

    private OtherMsg other;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getLastPacket() {
        return lastPacket;
    }

    public void setLastPacket(int lastPacket) {
        this.lastPacket = lastPacket;
    }

    public String getMediaData() {
        return mediaData;
    }

    public void setMediaData(String mediaData) {
        this.mediaData = mediaData;
    }

    public OtherMsg getOther() {
        return other;
    }

    public void setOther(OtherMsg other) {
        this.other = other;
    }
}
