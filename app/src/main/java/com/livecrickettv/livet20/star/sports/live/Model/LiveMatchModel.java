package com.livecrickettv.livet20.star.sports.live.Model;

import java.util.List;

public class LiveMatchModel {

    public String result;
    public Matches matches;
    public boolean status;

    public static class Matches {
        public List<STARTED> STARTED;

        public static class STARTED {
            public String lowestDefended;
            public int joinedContest;
            public String infoMsg;
            public boolean isCreditAvailable;
            public String official;
            public int type;
            public String referee;
            public String t1Flag;
            public boolean playingavailable;
            public String cfllIcon;
            public String bowlingFirst;
            public String winnerTeam;
            public int team1Id;
            public int id;
            public String lowestTotal;
            public String t2Flag;
            public int t2wk;
            public String toss;
            public boolean livelineavailable;
            public long start_time;
            public String firstInningAvg;
            public String thirdUmpire;
            public String header;
            public String scIcon;
            public String lambiTeam1;
            public String lambiTeam2;
            public int status;
            public String venue;
            public String head2head;
            public int team2Id;
            public boolean isTeamExported;
            public String t1over;
            public int sid;
            public I4details i4details;
            public int t1run;
            public String subtype;
            public String team2Name;
            public String highestTotal;
            public I2details i2details;
            public String highestChased;
            public I1details i1details;
            public I3details i3details;
            public int t1wk;
            public NewlivelineMessage newlivelineMessage;
            public String matchName;
            public String battingFirst;
            public long end_time;
            public String matchKey;
            public String predictions;
            public String secondInningAvg;
            public String team1Name;
            public String matchWonBy;
            public String t2over;
            public int t2run;
            public String sname;
            public String disicion;

            public static class I4details {
                public String over;
                public int inid;
                public int wk;
                public int batTeamId;
                public int BowlTeamId;
                public int mId;
                public int run;
                public int id;

            }

            public static class I2details {
                public String over;
                public int inid;
                public int wk;
                public int batTeamId;
                public int BowlTeamId;
                public int mId;
                public int run;
                public int id;

            }

            public static class I1details {
                public String over;
                public int inid;
                public int wk;
                public int batTeamId;
                public int BowlTeamId;
                public int mId;
                public int run;
                public int id;

            }

            public static class I3details {
                public String over;
                public int inid;
                public int wk;
                public int batTeamId;
                public int BowlTeamId;
                public int mId;
                public int run;
                public int id;

            }

            public static class NewlivelineMessage {
                public String result;
                public ScoreX score;
                public SessionX session;
                public boolean status;

                public static class ScoreX {
                    public Score score;
                    public String v;
                    public String mid;
                    public String type;


                    public static class Score {
                        public String r2;
                        public String lmb;
                        public String bot;
                        public String bosn;
                        public String f1;
                        public String bm;
                        public String f2;
                        public String bowl;
                        public String bo;
                        public String b1;
                        public String sc;
                        public String b2;
                        public String br;
                        public I4detailsX i4details;
                        public String team1Session;
                        public String bt;
                        public String bat1;
                        public String bat2;
                        public String mb;
                        public String bw;
                        public String team2Session;
                        public I2detailsX i2details;
                        public String s1;
                        public I1detailsX i1details;
                        public I3detailsX i3details;
                        public String s2;
                        public String oo;
                        public String or;
                        public String mt;
                        public String ow;
                        public String co;
                        public String m;
                        public String o;
                        public String tc;
                        public String inid;
                        public String r;
                        public String t;
                        public String w;
                        public String bsn;
                        public String lmbo;
                        public String ba;
                        public String r1;
                        public List<String> l6;

                        public static class I4detailsX {
                            public String r;
                            public String w;
                            public String tname;
                            public String tid;
                            public String o;

                        }

                        public static class I2detailsX {
                            public String r;
                            public String w;
                            public String tname;
                            public String tid;
                            public String o;
                        }

                        public static class I1detailsX {
                            public String r;
                            public String w;
                            public String tname;
                            public String tid;
                            public String o;

                        }

                        public static class I3detailsX {
                            public String r;
                            public String w;
                            public String tname;
                            public String tid;
                            public String o;

                        }
                    }
                }

                public static class SessionX {
                    public String v;
                    public Session session;
                    public String mid;
                    public String type;

                    public static class Session {
                        public String mcml;
                        public String mcTeleLink;
                        public String t3m2;
                        public String t1m1;
                        public String mcomment;
                        public String mb;
                        public String t2m2;
                        public String t3m1;
                        public String mc;
                        public String t1m2;
                        public String t2m1;
                        public String s1;
                        public String s2;
                        public String over;
                        public String f;
                        public String mt;
                        public String mscTeleLink;
                        public String co;
                        public String o;
                        public String r;
                        public String mcmlTeleLink;
                        public String msc;
                        public String t1;
                        public String t2;
                        public String t3;
                    }
                }
            }
        }
    }
}
