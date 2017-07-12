package com.max.helloandroid.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by WangHuaGui on 2017/7/12 10:57
 */

public class NewsListBean {
    @SerializedName("T1348647909107")
    private List<HeadLineBean> headLineBean;

    public List<HeadLineBean> getHeadLineBean() {
        return headLineBean;
    }

    public void setHeadLineBean(List<HeadLineBean> headLines) {
        this.headLineBean = headLines;
    }

    public static class HeadLineBean {
        @SerializedName("postid")
        private String postid;
        @SerializedName("hasCover")
        private boolean hasCover;
        @SerializedName("hasHead")
        private int hasHead;
        @SerializedName("replyCount")
        private int replyCount;
        @SerializedName("ltitle")
        private String ltitle;
        @SerializedName("hasImg")
        private int hasImg;
        @SerializedName("digest")
        private String digest;
        @SerializedName("hasIcon")
        private boolean hasIcon;
        @SerializedName("docid")
        private String docid;
        @SerializedName("title")
        private String title;
        @SerializedName("order")
        private int order;
        @SerializedName("priority")
        private int priority;
        @SerializedName("lmodify")
        private String lmodify;
        @SerializedName("boardid")
        private String boardid;
        @SerializedName("topic_background")
        private String topicBackground;
        @SerializedName("url_3w")
        private String url3w;
        @SerializedName("template")
        private String template;
        @SerializedName("votecount")
        private int votecount;
        @SerializedName("alias")
        private String alias;
        @SerializedName("cid")
        private String cid;
        @SerializedName("url")
        private String url;
        @SerializedName("hasAD")
        private int hasAD;
        @SerializedName("source")
        private String source;
        @SerializedName("ename")
        private String ename;
        @SerializedName("subtitle")
        private String subtitle;
        @SerializedName("imgsrc")
        private String imgsrc;
        @SerializedName("tname")
        private String tname;
        @SerializedName("ptime")
        private String ptime;
        @SerializedName("skipID")
        private String skipID;
        @SerializedName("skipType")
        private String skipType;
        @SerializedName("photosetID")
        private String photosetID;
        @SerializedName("imgsum")
        private int imgsum;
        @SerializedName("TAGS")
        private String TAGS;
        @SerializedName("TAG")
        private String TAG;
        @SerializedName("ads")
        private List<AdsBean> ads;
        @SerializedName("imgextra")
        private List<ImgextraBean> imgextra;

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getTopicBackground() {
            return topicBackground;
        }

        public void setTopicBackground(String topicBackground) {
            this.topicBackground = topicBackground;
        }

        public String getUrl3w() {
            return url3w;
        }

        public void setUrl3w(String url3w) {
            this.url3w = url3w;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public String getPhotosetID() {
            return photosetID;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public int getImgsum() {
            return imgsum;
        }

        public void setImgsum(int imgsum) {
            this.imgsum = imgsum;
        }

        public String getTAGS() {
            return TAGS;
        }

        public void setTAGS(String TAGS) {
            this.TAGS = TAGS;
        }

        public String getTAG() {
            return TAG;
        }

        public void setTAG(String TAG) {
            this.TAG = TAG;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<ImgextraBean> getImgextra() {
            return imgextra;
        }

        public void setImgextra(List<ImgextraBean> imgextra) {
            this.imgextra = imgextra;
        }

        public static class AdsBean {
            @SerializedName("title")
            private String title;
            @SerializedName("skipID")
            private String skipID;
            @SerializedName("tag")
            private String tag;
            @SerializedName("imgsrc")
            private String imgsrc;
            @SerializedName("subtitle")
            private String subtitle;
            @SerializedName("skipType")
            private String skipType;
            @SerializedName("url")
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSkipID() {
                return skipID;
            }

            public void setSkipID(String skipID) {
                this.skipID = skipID;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getSkipType() {
                return skipType;
            }

            public void setSkipType(String skipType) {
                this.skipType = skipType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ImgextraBean {
            @SerializedName("imgsrc")
            private String imgsrc;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }
        }
    }
}
