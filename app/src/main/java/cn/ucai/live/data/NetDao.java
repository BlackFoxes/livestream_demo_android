package cn.ucai.live.data;

import android.content.Context;

import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.domain.User;

import java.io.File;

import cn.ucai.live.utils.OnCompleteListener;
import cn.ucai.live.I;
import cn.ucai.live.utils.MD5;
import cn.ucai.live.utils.OkHttpUtils;

/**
 * Created by clawpo on 2017/2/8.
 */

public class NetDao {
    public static void register(Context context, String username, String nick, String password,
                                OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, nick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    public static void unRegister(Context context, String username,
                                  OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void login(Context context, String username, String password,
                             OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }

    public static void getUserInfoByUsername(Context context, String username,
                                             OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void updateUsernick(Context context, String username, String usernick,
                                      OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, usernick)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void uploadUserAvatar(Context context, String username, File file,
                                        OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID, username)
                .addParam(I.AVATAR_TYPE, I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    public static void addContact(Context context, String username, String cname,
                                  OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME, username)
                .addParam(I.Contact.CU_NAME, cname)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadContact(Context context, String username, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void removeContact(Context context, String username, String cname,
                                     OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME, username)
                .addParam(I.Contact.CU_NAME, cname)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void createGroup(Context context, EMGroup group, File file, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, group.getGroupId())
                .addParam(I.Group.NAME, group.getGroupName())
                .addParam(I.Group.DESCRIPTION, group.getDescription())
                .addParam(I.Group.OWNER, group.getOwner())
                .addParam(I.Group.IS_PUBLIC, String.valueOf(group.isPublic()))
                .addParam(I.Group.ALLOW_INVITES, String.valueOf(group.isAllowInvites()))
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    public static void addGroupMembers(Context context, String members, String hxid,
                                       OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MEMBERS)
                .addParam(I.Member.USER_NAME, members)
                .addParam(I.Member.GROUP_HX_ID, hxid)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadLiveList(Context context, OnCompleteListener listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GET_ALL_CHATROOM)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void createLive(Context context, User user, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_CHATROOM)
                .addParam("auth", "1IFgE")
                .addParam("name", user.getMUserNick() + "的直播")
                .addParam("description", user.getMUserNick() + "的直播")
                .addParam("owner", user.getMUserName())
                .addParam("maxusers", "300")
                .addParam("members", user.getMUserName())
                .targetClass(String.class)
                .execute(listener);


    }

    public static void removeLive(Context context, String chatroomId, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CHATROOM)
                .addParam("auth", "1IFgE")
                .addParam("chatRoomId", chatroomId)
                .targetClass(String.class)
                .execute(listener);


    }

    public static void loadAllGift(Context context, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ALL_GIFTS)
                .targetClass(String.class)
                .execute(listener);

    }

    public static void loadChange(Context context, String username,  OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_BALANCE)
                .addParam("uname", username)
                .targetClass(String.class)
                .execute(listener);


    }


    public static void givindGift(Context context, String username, String anchor,int giftId,int count, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GIVING_GIFT)
                .addParam("uname", username)
                .addParam("anchor",anchor)
                .addParam("giftId",String.valueOf(giftId))
                .addParam("giftNum",String.valueOf(count))
                .targetClass(String.class)
                .execute(listener);


    }


    public static void loadGiftStatementsGiving(Context context, String username,int pageId,int pageSize, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_RECEIVING_GIFT_STATEMENT)
                .addParam("uname", username)
                .addParam("pageId",String.valueOf(pageId))
                .addParam("pageSize",String.valueOf(pageSize))
                .targetClass(String.class)
                .execute(listener);


    }




    public static void loadGiftStatementsReceiving(Context context, String anchor,int pageId,int pageSize, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_RECEIVING_GIFT_STATEMENT)
                .addParam("anchor", anchor)
                .addParam("pageId",String.valueOf(pageId))
                .addParam("pageSize",String.valueOf(pageSize))
                .targetClass(String.class)
                .execute(listener);


    }





}
