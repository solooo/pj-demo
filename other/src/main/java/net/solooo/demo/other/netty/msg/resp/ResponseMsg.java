package net.solooo.demo.other.netty.msg.resp;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class ResponseMsg {

    /**
     * aid : appId
     * version : 0.1.0
     * cmd : upload
     * type : resp
     * error :
     * data : {"seq":1,"oid":""}
     */

    private String aid;

    private String version;

    private String cmd;

    private String type;

    private String error;

    private DataBean data;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * seq : 1
         * oid :
         */

        private int seq;

        private String oid;

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
    }
}
