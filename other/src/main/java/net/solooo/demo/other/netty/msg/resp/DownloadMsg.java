package net.solooo.demo.other.netty.msg.resp;

/**
 * Description:
 * Author:Eric
 * Date:2017/7/12
 */
public class DownloadMsg {

    /**
     * aid : MediaCloudStore
     * cmd : download
     * data : {"lastPacket":0,"mediaData":"","requestId":"123","seq":14811137}
     * type : resp
     * version : 0.1.0
     */

    private String aid;

    private String cmd;

    private DataBean data;

    private String type;

    private String version;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        /**
         * lastPacket : 0
         * mediaData :
         * requestId : 123
         * seq : 14811137
         */

        private int lastPacket;

        private String mediaData;

        private String requestId;

        private int seq;

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

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }
    }
}
