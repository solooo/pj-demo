package net.solooo.demo.other.netty.msg.req;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class RequestMsg {

    /**
     * aid : appId
     * version : 0.1.0
     * cmd : upload
     * type : req
     * error :
     * data : {"seq":1,"oid":"","lastPacket":0,"mediaData":"","other":{"posID":"","fileName":"","fileLength":"","fileStartTime":0,"fileStopTime":0,"frameIndexFileData":""}}
     */

    private String aid;

    private String version;

    private String cmd;

    private String type;

    private String error;

    private DataMsg data;

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

    public DataMsg getData() {
        return data;
    }

    public void setData(DataMsg data) {
        this.data = data;
    }
}
