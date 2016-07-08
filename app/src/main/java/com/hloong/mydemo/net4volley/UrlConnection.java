package com.hloong.mydemo.net4volley;

import android.content.Context;

import java.util.Locale;

import cn.zswy.qfbao.Session;
import cn.zswy.qfbao.bean.ActiveAccountRequest;
import cn.zswy.qfbao.bean.AllFinanciaListRequest;
import cn.zswy.qfbao.bean.AssertRecordDetailRequst;
import cn.zswy.qfbao.bean.BankSupportLimitRequest;
import cn.zswy.qfbao.bean.BillRequest;
import cn.zswy.qfbao.bean.BousDetailRequest;
import cn.zswy.qfbao.bean.ChangePhoneCheckVercodeRequest;
import cn.zswy.qfbao.bean.CheckBankCardRequest;
import cn.zswy.qfbao.bean.CheckMobileRequest;
import cn.zswy.qfbao.bean.CheckPasswordRequest;
import cn.zswy.qfbao.bean.CheckVercodeRequest;
import cn.zswy.qfbao.bean.CheckVerifyRequest;
import cn.zswy.qfbao.bean.CurrentRegularRequest;
import cn.zswy.qfbao.bean.FeedbackDetailsRequest;
import cn.zswy.qfbao.bean.FeedbackRecordRequest;
import cn.zswy.qfbao.bean.GetAboutRequest;
import cn.zswy.qfbao.bean.GetAgreementRequest;
import cn.zswy.qfbao.bean.GetVercodeRequest;
import cn.zswy.qfbao.bean.HomePageRequst;
import cn.zswy.qfbao.bean.IndexTipsRequest;
import cn.zswy.qfbao.bean.InvestBuyResultRequest;
import cn.zswy.qfbao.bean.InvestCouponRequest;
import cn.zswy.qfbao.bean.InvestQfbRequest;
import cn.zswy.qfbao.bean.InvestRequest;
import cn.zswy.qfbao.bean.InvestRuleRequest;
import cn.zswy.qfbao.bean.LoginRequest;
import cn.zswy.qfbao.bean.MessageCenterRequest;
import cn.zswy.qfbao.bean.MessageNotifyDetailRequest;
import cn.zswy.qfbao.bean.MyAllContactsRequest;
import cn.zswy.qfbao.bean.MyCouponsListRequest;
import cn.zswy.qfbao.bean.MyInvestRequest;
import cn.zswy.qfbao.bean.MyRequest;
import cn.zswy.qfbao.bean.NickNameRequest;
import cn.zswy.qfbao.bean.OtherAssetRecordRequest;
import cn.zswy.qfbao.bean.OtherBindBankCardRequest;
import cn.zswy.qfbao.bean.OtherPayGetCodeRequest;
import cn.zswy.qfbao.bean.OtherPayValidateRequest;
import cn.zswy.qfbao.bean.PaymentRequest;
import cn.zswy.qfbao.bean.ProjectDetailRequest;
import cn.zswy.qfbao.bean.ProjectInvestmentRecordsRequest;
import cn.zswy.qfbao.bean.PutFeedbackRequest;
import cn.zswy.qfbao.bean.QfbBackRequest;
import cn.zswy.qfbao.bean.QfbBackRuleRequest;
import cn.zswy.qfbao.bean.QfbRequest;
import cn.zswy.qfbao.bean.QuestionRequest;
import cn.zswy.qfbao.bean.RFP_ListRequest;
import cn.zswy.qfbao.bean.RealNameRequest;
import cn.zswy.qfbao.bean.RegisterRequest;
import cn.zswy.qfbao.bean.RegularRecordRequest;
import cn.zswy.qfbao.bean.ResetPasswordRequest;
import cn.zswy.qfbao.bean.RmemberRequest;
import cn.zswy.qfbao.bean.SetPayPasswordRequest;
import cn.zswy.qfbao.bean.ShareRequest;
import cn.zswy.qfbao.bean.VersionRequest;
import cn.zswy.qfbao.bean.WithdrawRequest;
import cn.zswy.qfbao.bean.WithdrawRuleRequest;
import cn.zswy.qfbao.constant.UrlConstants;
import cn.zswy.qfbao.net.ApiManager.TaskResultPicker;
import cn.zswy.qfbao.util.PassWordUtil;

/**
 * 网络连接，所有网络请求方法集合
 * 
 * @author hloong
 */
public class UrlConnection {
    static Context mContext;

    String tag;

    public static UrlConnection mUrlConnection = null;

    public static UrlConnection getInstance(Context context) {
        mContext = context;
        if (mUrlConnection == null) {
            mUrlConnection = new UrlConnection();
        }
        return mUrlConnection;
    }

    /**
     * 检查用户是否完成实名认证
     * 
     * @param taskResultPicker
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @param TAG
     */
    public void getCheckVerifyRequest(TaskResultPicker taskResultPicker) {
        CheckVerifyRequest mRequest = new CheckVerifyRequest();
        mRequest.setMethodName(UrlConstants.check_verify);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, false, "Check_Verify", false, true);
    }
    
    /**
     * 激活中视账户
     * 
     * @param taskResultPicker
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @param TAG
     */
    public void getActiveAccount(TaskResultPicker taskResultPicker,String access_token) {
        ActiveAccountRequest mRequest = new ActiveAccountRequest();
        mRequest.setAccess_token(access_token);
        mRequest.setMethodName(UrlConstants.active_account);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, false, "Active_account", false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, false, "Active_account", false, true);
        }
    }
    

    /**
     * 检测更新 Index
     * 
     * @param taskResultPicker
     * @param TAG
     * @param version
     *            当前版本号
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getIndexVersionRequest(TaskResultPicker taskResultPicker, String TAG, int ver_code) {
        VersionRequest mRequest = new VersionRequest();
        mRequest.setVer_code(ver_code);
        mRequest.setMethodName(UrlConstants.INDEX_UPDATE);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, false, TAG, true, false);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, false, TAG, true, false);
        }
    }

    /**
     * 登录
     * 
     * @param taskResultPicker
     * @param TAG
     * @param version
     *            当前版本号
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getLoginRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String password, String login_sign, String login_random) {
        LoginRequest mLoginRequest = new LoginRequest();
        mLoginRequest.setMobile(mobile);
        mLoginRequest.setPassword(PassWordUtil.EncryptPassWord(password));
        mLoginRequest.setLogin_sign(login_sign);
        mLoginRequest.setLogin_random(login_random);
        mLoginRequest.setMethodName(UrlConstants.LOGIN);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mLoginRequest, taskResultPicker, true, TAG, false, false);
        } else {
            ApiManager.executeTask(mContext, mLoginRequest, taskResultPicker, true, TAG, false, false);
        }
    }

    /**
     * 获取验证码
     * 
     * @param taskResultPicker
     * @param TAG
     * @param mobile
     *            手机号
     * @param type
     *            请求类型 1.设置密码的验证码 2.设置支付密码的验证码 3.申请提现的验证码 4.申请积分的验证码 5.兑换积分的验证
     *            6.用户注册的验证 7.添加银行卡验证码 8.用户忘记密码 9.用户贷款验证码 10.用户担保的验证码 13.忘记支付密码
     *            14.修改银行卡 15.表示检测更换设备登录的获取验证码
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getVercodeRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String checkrandom, String checksign, int type) {
        GetVercodeRequest mGetVercodeRequest = new GetVercodeRequest();
        mGetVercodeRequest.setMobile(mobile);
        mGetVercodeRequest.setCheckrandom(checkrandom);
        mGetVercodeRequest.setChecksign(checksign);
        mGetVercodeRequest.setType(type);
        mGetVercodeRequest.setMethodName(UrlConstants.getvercode);
        ApiManager.executeTask(mContext, mGetVercodeRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 获取协议
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            请求类型 1.注册的协议 2.添加银行卡服务协议 3.充值服务协议 4.钱富宝转移用户的协议
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getAgreementRequestCon(TaskResultPicker taskResultPicker, String TAG, int type) {
        GetAgreementRequest mGetAgreementRequest = new GetAgreementRequest();
        mGetAgreementRequest.setType(type);
        mGetAgreementRequest.setMethodName(UrlConstants.getagreement);

        ApiManager.executeTask(mContext, mGetAgreementRequest, taskResultPicker, false, TAG, false, false);
    }

    /**
     * 验证验证码
     * 
     * @param taskResultPicker
     * @param TAG
     * @param mobile
     *            手机号
     * @param code
     *            验证码
     * @param type
     *            请求类型 请求类型 1.设置密码的验证码 2.设置支付密码的验证码 3.申请提现的验证码 4.申请积分的验证码
     *            5.兑换积分的验证 6.用户注册的验证 7.添加银行卡验证码 8.用户忘记密码 9.用户贷款验证码 10.用户担保的验证码 15.验证切换设备
     *            13.忘记支付密码
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void CheckVercodeRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String code, int type) {
        CheckVercodeRequest mCheckVercodeRequest = new CheckVercodeRequest();
        mCheckVercodeRequest.setMobile(mobile);
        mCheckVercodeRequest.setType(type);
        mCheckVercodeRequest.setCode(code);
        mCheckVercodeRequest.setMethodName(UrlConstants.checkauthcode);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mCheckVercodeRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mCheckVercodeRequest, taskResultPicker, true, TAG, false, true);
        }
    }
    
    /**
     * 切换设备验证验证码
     * 
     * @param taskResultPicker
     * @param TAG
     * @param mobile
     *            手机号
     * @param code
     *            验证码
     * @param type
     *            15.验证切换设备
     * @param token 
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void ChangePhoneCheckVercodeRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String code, int type, String token) {
        ChangePhoneCheckVercodeRequest mChangePhoneCheckVercodeRequest = new ChangePhoneCheckVercodeRequest();
        mChangePhoneCheckVercodeRequest.setMobile(mobile);
        mChangePhoneCheckVercodeRequest.setType(type);
        mChangePhoneCheckVercodeRequest.setCode(code);
        mChangePhoneCheckVercodeRequest.setAccess_token(token);
        mChangePhoneCheckVercodeRequest.setMethodName(UrlConstants.checkauthcode);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mChangePhoneCheckVercodeRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mChangePhoneCheckVercodeRequest, taskResultPicker, true, TAG, false, true);
        }
    }
    
    /**
     * 重置登录密码 非登录下操作
     * 
     * @param taskResultPicker
     * @param TAG
     * @param mobile
     *            手机号
     * @param pwd
     *            密码
     * @param code
     *            验证码
     * @param repwd_sign
     *            验签
     * @param repwd_random
     *            随机数
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void ResetPassWordRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String pwd, String code, String repwd_sign, String repwd_random) {
        ResetPasswordRequest mResetPasswordRequest = new ResetPasswordRequest();
        mResetPasswordRequest.setCode(code);
        mResetPasswordRequest.setMobile(mobile);
        mResetPasswordRequest.setPassword(PassWordUtil.EncryptPassWord(pwd));
        mResetPasswordRequest.setRepwd_sign(repwd_sign);
        mResetPasswordRequest.setRepwd_random(repwd_random);
        mResetPasswordRequest.setMethodName(UrlConstants.Resetpwd);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mResetPasswordRequest, taskResultPicker, true, TAG, false, false);
        } else {
            ApiManager.executeTask(mContext, mResetPasswordRequest, taskResultPicker, true, TAG, false, false);
        }
    }

    /**
     * 设置支付密码
     * 
     * @param taskResultPicker
     * @param TAG
     * @param pwd
     *            支付密码
     * @param code
     *            手机验证码
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getSetPayPassWordRequestCon(TaskResultPicker taskResultPicker, String TAG, String pwd) {
        SetPayPasswordRequest mSetPayPasswordRequest = new SetPayPasswordRequest();
        mSetPayPasswordRequest.setZf_pwd(PassWordUtil.EncryptPassWord(pwd));
        mSetPayPasswordRequest.setMethodName(UrlConstants.setpaypwd);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mSetPayPasswordRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mSetPayPasswordRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 校验密码
     * 
     * @param taskResultPicker
     * @param TAG
     * @param pwd
     *            支付密码
     * @param type
     *            1表示验证登录密码，2表示验证支付密码
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void CheckPwdRequestCon(TaskResultPicker taskResultPicker, String TAG, String pwd, int type) {
        CheckPasswordRequest mCheckPasswordRequest = new CheckPasswordRequest();
        mCheckPasswordRequest.setPwd(PassWordUtil.EncryptPassWord(pwd));
        mCheckPasswordRequest.setType(type);
        mCheckPasswordRequest.setMethodName(UrlConstants.checkpwd);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mCheckPasswordRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mCheckPasswordRequest, taskResultPicker, true, TAG, false, true);
        }
       
    }

    /**
     * 直接注册
     * 
     * @param taskResultPicker
     * @param TAG
     * @param mobile
     *            手机号
     * @param pwd
     *            密码
     * @param code
     *            验证码
     * @param r_member
     *            邀请码(非必填)
     * @param session_id
     *            {@link Session}
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getRegisterRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile, String pwd, String code, String r_member, String register_sign, String register_random) {
        RegisterRequest mRequest = new RegisterRequest();
        mRequest.setMobile(mobile);
        mRequest.setPassword(PassWordUtil.EncryptPassWord(pwd));
        mRequest.setCode(code);
        mRequest.setRegister_sign(register_sign);
        mRequest.setRegister_random(register_random);
        mRequest.setR_mobile(r_member);
        mRequest.setMethodName(UrlConstants.Register);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, false);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, false);
        }
    }


    /**
     * 设置/修改昵称
     * 
     * @param taskResultPicker
     * @param TAG
     * @param nickname
     *            昵称
     */
    public void setNickNameRequestCon(TaskResultPicker taskResultPicker,
            String TAG, String nickname) {
        NickNameRequest mNickNameRequest = new NickNameRequest();
        mNickNameRequest.setNickname(nickname);
        mNickNameRequest.setMethodName(UrlConstants.NickName);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mNickNameRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mNickNameRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 修改推荐人
     * 
     * @param taskResultPicker
     * @param TAG
     * @param r_mobile
     *            推荐人手机号
     */
    public void setRemmberRequestCon(TaskResultPicker taskResultPicker, String TAG, String r_mobile) {
        RmemberRequest mRmemberRequest = new RmemberRequest();
        mRmemberRequest.setR_mobile(r_mobile);
        mRmemberRequest.setMethodName(UrlConstants.update_rmember);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRmemberRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRmemberRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 钱富宝 理财
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void getQfbCon(TaskResultPicker taskResultPicker, String TAG, int product_id, boolean isShowDialog) {
        QfbRequest mRequest = new QfbRequest();
        mRequest.setProduct_id(product_id);
        mRequest.setMethodName(UrlConstants.qfb);
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, isShowDialog, TAG, false, true);
    }

    /**
     * 实名认证
     * 
     * @param taskResultPicker
     * @param TAG
     * @param realName
     *            实名
     * @param idCard
     *            身份证号
     * @param imei
     *            设备唯一标识
     * @param auth_key
     *            认证钥匙
     * @param auth_random
     *            随机参数
     */
    public void setRealNameRequestCon(TaskResultPicker taskResultPicker, String TAG, String realName, String idCard, String auth_key, String auth_random) {
        RealNameRequest mRealNameRequest = new RealNameRequest();
        Locale defloc = Locale.getDefault();
        mRealNameRequest.setRealname(realName);
        mRealNameRequest.setIdcard(idCard.toUpperCase(defloc));//将字母强制转为大写
        mRealNameRequest.setMethodName(UrlConstants.realname);
        // mRealNameRequest.setImei(imei);
        mRealNameRequest.setAuth_key(auth_key);
        mRealNameRequest.setAuth_random(auth_random);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRealNameRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRealNameRequest, taskResultPicker, true, TAG, false, true);
        }
    }


    /**
     * V1.5.0 第三方支付添加银行卡
     * 
     * @param taskResultPicker
     * @param TAG
     * @param no
     *            银行卡号
     * @param mobile
     *            手机号，不同平台请求，允许无值
     * @param province
     *            ;//开户省（非必传，中国银行与招商银行必传，后台做好验证）
     * @param city
     *            ;//开户市（非必传，中国银行与招商银行必传，后台做好验证）
     * @param type
     *            1表示京东支付（弃用），4表示易联（弃用），5表示快钱，6表示证联
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void OtherBindBankCardRequestCon(TaskResultPicker taskResultPicker, String TAG, String name, String id_card, String no, String mobile, String province, String city, int type) {

        OtherBindBankCardRequest mOtherBindBankCardRequest = new OtherBindBankCardRequest();
        Locale defloc = Locale.getDefault();
        mOtherBindBankCardRequest.setName(name);
        mOtherBindBankCardRequest.setId_card(id_card.toUpperCase(defloc));//将字母强制转为大写
        mOtherBindBankCardRequest.setNo(no);
        mOtherBindBankCardRequest.setMobile(mobile);
        mOtherBindBankCardRequest.setProvince(province);
        mOtherBindBankCardRequest.setCity(city);
        mOtherBindBankCardRequest.setType(type);
        mOtherBindBankCardRequest.setMethodName(UrlConstants.other_bind_bank_card);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mOtherBindBankCardRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mOtherBindBankCardRequest, taskResultPicker, true, TAG, false, true);
        }
    }


    /**
     * 获取关于
     * 
     * @param taskResultPicker
     * @param TAG
     * @param
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getAboutRequestCon(TaskResultPicker taskResultPicker, String TAG) {
        GetAboutRequest mAboutRequest = new GetAboutRequest();
        mAboutRequest.setMethodName(UrlConstants.about);
        ApiManager.executeTask(mContext, mAboutRequest, taskResultPicker, false, TAG, false, true);
    }

    /**
     * 提交意见反馈
     * 
     * @param taskResultPicker
     * @param TAG
     * @param content
     *            意见反馈 内容
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void putFeedbackRequestCon(TaskResultPicker taskResultPicker, String TAG, String content) {
        PutFeedbackRequest mPutFeedbackRequest = new PutFeedbackRequest();
        mPutFeedbackRequest.setContent(content);
        mPutFeedbackRequest.setMethodName(UrlConstants.put_feedback);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mPutFeedbackRequest, taskResultPicker, false, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mPutFeedbackRequest, taskResultPicker, false, TAG, false, true);
        }
    }

    /**
     * 我的反馈记录
     * 
     * @param taskResultPicker
     * @param TAG
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     */
    public void getFeedbackRecord(TaskResultPicker taskResultPicker, String TAG, int limit, int page) {
        FeedbackRecordRequest mRequest = new FeedbackRecordRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setMethodName(UrlConstants.feedback_record);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 反馈详情
     * 
     * @param taskResultPicker
     * @param TAG
     * @param id
     *            反馈id
     */
    public void getFeedbackDetails(TaskResultPicker taskResultPicker, String TAG, int id) {
        FeedbackDetailsRequest mRequest = new FeedbackDetailsRequest();
        mRequest.setId(id);
        mRequest.setMethodName(UrlConstants.feedback_details);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 我的
     * 
     * @param taskResultPicker
     * @param TAG
     * @param limit
     * @param page
     */
    public void getMyCon(TaskResultPicker taskResultPicker, String TAG, boolean isShowDialog) {
        MyRequest mRequest = new MyRequest();
        mRequest.setMethodName(UrlConstants.MONEY_MYMONEY);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, isShowDialog, TAG, false, true);
    }

    /**
     * 校验银行卡
     * 
     * @param taskResultPicker
     * @param TAG
     * @param card
     *            银行卡号
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getCheckBankCardRequest(TaskResultPicker taskResultPicker, String TAG, String card,int payment) {
        CheckBankCardRequest mCheckBankCardRequest = new CheckBankCardRequest();
        mCheckBankCardRequest.setCard(card);
        mCheckBankCardRequest.setPayment(payment);
        mCheckBankCardRequest.setMethodName(UrlConstants.checkBankCard);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mCheckBankCardRequest, taskResultPicker, false, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mCheckBankCardRequest, taskResultPicker, false, TAG, false, true);
        }
    }

    /**
     * 查询手机号码对应的人名
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void getNameByPhoneRequestCon(TaskResultPicker taskResultPicker, String TAG, String mobile) {
        CheckMobileRequest request = new CheckMobileRequest();
        request.setMobile(mobile);
        request.setMethodName(UrlConstants.Is_Phone);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, request, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
        }
    }


    /**
     * 常见问题
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            1表示常见问题，2表示理财转入说明，3表示理财转出说明，4表示版本说明，5表示零钱充值说明，6表示零钱提现说明
     * 
     */
    public void getQuestion(TaskResultPicker taskResultPicker, String TAG, int type) {
        QuestionRequest mRequest = new QuestionRequest();
        mRequest.setType(type);
        mRequest.setMethodName(UrlConstants.MONEY_QUESTION);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 首页公告
     * 
     * @param taskResultPicker
     * @param TAG
     * 
     */
    public void getIndexTipsRequest(TaskResultPicker taskResultPicker,
            String TAG) {
        IndexTipsRequest mRequest = new IndexTipsRequest();
        mRequest.setMethodName(UrlConstants.INDEX_TIPS);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, false, TAG, false, false);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, false, TAG, false, false);
        }
    }

    /**
     * 首页分享
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            1首页分享
     */
    public void getShare(TaskResultPicker taskResultPicker, String TAG, int type) {
        ShareRequest mRequest = new ShareRequest();
        mRequest.setType(type);
        mRequest.setMethodName(UrlConstants.GET_SHARE);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, false, TAG, false, true);
    }

    /**
     * 银行支持及限额
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            支付通道，固定为：1易联支付（弃用），4京东支付（弃用），5表示快钱支付 ，6表示证联；如果默认不请求此字段或字段值错误就表示所有通道支持的卡
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     */
    public void getBankSupportLimit(TaskResultPicker taskResultPicker, String TAG, int type, int limit, int page) {
        BankSupportLimitRequest mRequest = new BankSupportLimitRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setType(type);
        mRequest.setMethodName(UrlConstants.bank_support_limit);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 我的代金券
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            type=0 表示可用，type=1 表示查看历史
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     */
    public void getMyCouponsList(TaskResultPicker taskResultPicker, String TAG, int type, int limit, int page) {
        MyCouponsListRequest mRequest = new MyCouponsListRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setType(type);
        mRequest.setMethodName(UrlConstants.my_coupons);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /** V1.4.0 **/
    /**
     * 定期理财产品列表
     * 
     * @param taskResultPicker
     * @param TAG
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     */
    public void getRegularFundProductList(TaskResultPicker taskResultPicker, String TAG, int limit, int page) {
        RFP_ListRequest mRequest = new RFP_ListRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setMethodName(UrlConstants.RFP_LIST);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 待收收益明细
     * 
     * @param taskResultPicker
     * @param TAG
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     */
    public void getBonusDetailsPaidList(TaskResultPicker taskResultPicker, String TAG, int limit, int page) {
        BousDetailRequest mRequest = new BousDetailRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setMethodName(UrlConstants.REGULAR_WAITDETAIL);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
//        if (UrlConstants.Post) {
//            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
//        } else {
//            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
//        }
    }

    /**
     * 赎回
     * 
     * @param taskResultPicker
     * @param money
     *            赎回金额
     * @param zf_pwd
     *            支付密码
     * @param TAG
     */

    public void getQfbBackRequestCon(TaskResultPicker taskResultPicker, String money, String zf_pwd, String TAG) {
        QfbBackRequest request = new QfbBackRequest();
        request.setMethodName(UrlConstants.MONEY_BACK);
        request.setMoney(money);
        request.setZf_pwd(PassWordUtil.EncryptPassWord(zf_pwd));
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, request, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 赎回规则
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void getQfbBackRuleRequestCon(TaskResultPicker taskResultPicker, String TAG) {
        QfbBackRuleRequest request = new QfbBackRuleRequest();
        request.setMethodName(UrlConstants.MONEY_BACK_RULE);
        ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 提现接口
     * 
     * @param taskResultPicker
     * @param TAG
     * @param bank_id
     *            银行卡id
     * @param money
     *            提现金额
     * @param zf_pwd
     *            支付密码
     * @param type
     *            提现类型，1表示工作日到账，2表示1-3个工作日到账
     * @param payment
     */
    public void getWithdrawCon(TaskResultPicker taskResultPicker, String TAG, int bank_id, String money, String zf_pwd, int type, int payment) {
        WithdrawRequest mRequest = new WithdrawRequest();
        mRequest.setBank_id(bank_id);
        mRequest.setMoney(money);
        mRequest.setPayment(payment);
        mRequest.setType(type);
        mRequest.setZf_pwd(PassWordUtil.EncryptPassWord(zf_pwd));
        mRequest.setMethodName(UrlConstants.money_withdraw);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 提现规则
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void getWithdrawRuleCon(TaskResultPicker taskResultPicker, String TAG) {
        WithdrawRuleRequest request = new WithdrawRuleRequest();
        request.setMethodName(UrlConstants.MONEY_WITHDRAW_RULE);
        ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 购买定期活期的结果页面请求
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            1表示活期，2表示定期
     * @param order_id
     *            订单id
     */
    public void getInvestBuyResultCon(TaskResultPicker taskResultPicker, String TAG, int type, int order_id,int product_id) {
        InvestBuyResultRequest request = new InvestBuyResultRequest();
        request.setType(type);
        request.setProduct_id(product_id);
        request.setOrder_id(order_id);
        request.setMethodName(UrlConstants.MONEY_BUY_DETAIL);
        ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 购买定期活期输入金额返回代金券
     * 
     * @param taskResultPicker
     * @param TAG
     * @param money
     *            价格
     * @param product_id
     *            活期产品id
     */
    public void getInvestCouponBackCon(TaskResultPicker taskResultPicker, String TAG, String money, int product_id) {
        InvestCouponRequest request = new InvestCouponRequest();
        request.setMoney(money);
        request.setType(2);
        request.setProduct_id(product_id);
        request.setMethodName(UrlConstants.MONEY_BUY_BACK);
        ApiManager.executeTask(mContext, request, taskResultPicker, false, TAG, false, true);
    }

    /**
     * 投资中 （定期活期购买规则）
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     *            1表示活期，2表示定期
     * @param product_id
     *            定期id
     */
    public void getInvestingRuleCon(TaskResultPicker taskResultPicker, String TAG, int type, int product_id) {
        InvestRuleRequest mRequest = new InvestRuleRequest();
        mRequest.setMethodName(UrlConstants.MONEY_BUY_RULE);
        mRequest.setType(type);
        mRequest.setProduct_id(product_id);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 投资活期理财 购买
     * 
     * @param taskResultPicker
     * @param TAG
     * @param type
     * @param product_id
     * @param payment_type 1表示零钱，2表示证联
     * @param payment 固定为：1易联支付（弃用）,2支付宝支付，3零钱支付，4京东支付（弃用），5表示快钱支付，6表示证联快捷支付
     */
    public void getInvestBuyQfbCon(TaskResultPicker taskResultPicker, String TAG, int product_id, String money, String zf_pwd,int bank_id,int payment) {
        InvestQfbRequest mRequest = new InvestQfbRequest();
        mRequest.setMethodName(UrlConstants.CURRENT_CREATE);
        mRequest.setProduct_id(product_id);
        mRequest.setMoney(money);
        if (payment == 3) {//零钱支付
            mRequest.setZf_pwd(PassWordUtil.EncryptPassWord(zf_pwd));
        }else if(payment == 6){//证联支付
            mRequest.setBank_id(bank_id);
        }
        mRequest.setPayment(payment);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    
    /**
     * 投资定期理财 购买
     * @param taskResultPicker
     * @param TAG
     * @param id
     * @param member_voucher_id
     * @param money
     * @param zf_pwd
     * @param bank_id 
     * @param payment_type 1表示活期，2表示定期
     * @param payment 固定为：1易联支付（弃用）,2支付宝支付，3零钱支付，4京东支付（弃用），5表示快钱支付，6表示证联快捷支付
     */
    public void getInvestBuyRegularCon(TaskResultPicker taskResultPicker, String TAG, int id, int member_voucher_id, String money, String zf_pwd,int bank_id,int payment) {
        InvestRequest mRequest = new InvestRequest();
        mRequest.setMethodName(UrlConstants.REGULAR_CREATE);
        mRequest.setMoney(money);
        mRequest.setId(id);
        if (member_voucher_id != -1) {
            mRequest.setMember_voucher_id(member_voucher_id);
        } 
        if (payment == 3) {//零钱支付
            mRequest.setZf_pwd(PassWordUtil.EncryptPassWord(zf_pwd));
        }else if(payment == 6){//证联支付
            mRequest.setBank_id(bank_id);
        }
        mRequest.setPayment(payment);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 获取支付方式
     * 
     * @param taskResultPicker
     * @param pay_type;//1表示充值，2表示提现，3表示购买活期，4，表示购买定期
     * @param TAG
     */
    public void getPaytype(TaskResultPicker taskResultPicker, String TAG, int pay_type) {
        PaymentRequest request = new PaymentRequest();
        request.setType(pay_type);
        request.setMethodName(UrlConstants.PAY_TYPE);
        ApiManager.executeTask(mContext, request, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 第三方支付 短信验证
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void OtherPayValidate(TaskResultPicker taskResultPicker, String TAG, String code, String sn, int payment) {
        OtherPayValidateRequest mRequest = new OtherPayValidateRequest();
        mRequest.setCode(code);
        mRequest.setSn(sn);
        mRequest.setPayment(payment);
        mRequest.setMethodName(UrlConstants.OtherPayCommit);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 第三支付 获取验证码
     * 
     * @param taskResultPicker
     * @param TAG
     */
    public void OtherPayGetCode(TaskResultPicker taskResultPicker, String TAG, String sn,int payment) {
        OtherPayGetCodeRequest mRequest = new OtherPayGetCodeRequest();
        mRequest.setPayment(payment);
        mRequest.setSn(sn);
        mRequest.setMethodName(UrlConstants.OtherPayCommit_GetCode);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /************************************************************ 2.0 ***********************************************************/
    /**
     * 首页数据
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋
     */
    public void getHomePageRequest(TaskResultPicker taskResultPicker, String TAG) {
        HomePageRequst mApiRequest = new HomePageRequst();
        mApiRequest.setMethodName(UrlConstants.HOME_PAGE);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, false);
    }

    /**
     * 首页为你推荐列表
     * get
     * @param taskResultPicker
     * @param TAG 
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋           
     */
    public void getCurrentRegularRequest(TaskResultPicker taskResultPicker, String TAG) {
        CurrentRegularRequest mApiRequest = new CurrentRegularRequest();
        mApiRequest.setMethodName(UrlConstants.RECOMMEND_CURRENT_REGULAR);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, false, TAG, false, false);
    }

    /**
     * 定期详情 ProjectDetail  get
     * 
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋                
     */
    public void getProjectDetailRequest(TaskResultPicker taskResultPicker, String TAG, int id) {
        ProjectDetailRequest mApiRequest = new ProjectDetailRequest();
        mApiRequest.setId(id);
        mApiRequest.setMethodName(UrlConstants.PRODUCT_DETAIL);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 所有理财列表
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋      
     */
    public void getAllFinanciaListRequest(TaskResultPicker taskResultPicker, String TAG, int sort, int limit, int page, int type) {
        AllFinanciaListRequest mApiRequest = new AllFinanciaListRequest();
        mApiRequest.setSort(sort);
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setType(type);
        mApiRequest.setMethodName(UrlConstants.INDEX_ALL);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, false);
    }

    /**
     * 我的人脉 
     * Post
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋
     */
    public void getAllMyContactsListRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page, int type) {
        MyAllContactsRequest mApiRequest = new MyAllContactsRequest();
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setType(type);
        mApiRequest.setMethodName(UrlConstants.MONEY_CONTACTS);
        if (UrlConstants.Post) {
            ApiManager.executeTaskPost(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
        } else {
            ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
        }
    }

    /**
     * 消息中心
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋
     */
    public void getMessageCenterListRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page, int type) {
        MessageCenterRequest mApiRequest = new MessageCenterRequest();
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setType(type);
        mApiRequest.setMethodName(UrlConstants.MESSAGE_LIST);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
        
    }
    
    /**
     * 消息详情
     * get
     * @param taskResultPicker
     * @param TAG
     * @param id
     * @author 郑昌奋
     */
    public void getMessageNotifyDetail(TaskResultPicker taskResultPicker, String TAG, int id, int type) {
        MessageNotifyDetailRequest mRequest = new MessageNotifyDetailRequest();
        mRequest.setId(id);
        mRequest.setType(type);
        mRequest.setMethodName(UrlConstants.MESSAGE_CONTENT);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 账单  我的资产记录（31）
     * get
     * @param taskResultPicker
     * @param TAG
     * @param limit
     *            一页显示多少数据
     * @param page
     *            多少页，默认1
     * @author 郑昌奋          
     */
    public void getBillRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page) {
        BillRequest mRequest = new BillRequest();
        mRequest.setLimit(limit);
        mRequest.setPage(page);
        mRequest.setType(0);
        mRequest.setMethodName(UrlConstants.BILL_LIST);
        ApiManager.executeTask(mContext, mRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 我的资产记录之充值提现定期活期四种列表封装类
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋             
     */
    public void getOtherAssetRecordListRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page, int type) {
        OtherAssetRecordRequest mApiRequest = new OtherAssetRecordRequest();
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setType(type);
        mApiRequest.setMethodName(UrlConstants.MY_RECORD);
            ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 我的资产记录详情v200 (Get)
     * 资产详情 type;//1表示充值记录，2表示提现记录，3表示投资理财记录（定期理财），4表示活期赎回记录
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋   
     */
    public void getAllAssertRecordDetailRequest(TaskResultPicker taskResultPicker, String TAG, int id, int type) {
        AssertRecordDetailRequst mApiRequest = new AssertRecordDetailRequst();
        mApiRequest.setId(id);
        mApiRequest.setType(type);
        mApiRequest.setMethodName(UrlConstants.MY_RECORD_DETAIL);
            ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 我的投资 type;//为0表示全部项目，1表示持有中，2表示已退出
     * 
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     */
    public void getMyInvestRequestCon(TaskResultPicker taskResultPicker, String TAG, int page, int limit, int type) {
        MyInvestRequest mApiRequest = new MyInvestRequest();
        mApiRequest.setType(type);
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setMethodName(UrlConstants.MY_ALL);
            ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
    }

    /**
     * 资产记录  定期理财用户投资记录v200(Get)
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋            
     */
    public void getRegularRecordRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page, int id) {
        RegularRecordRequest mApiRequest = new RegularRecordRequest();
        mApiRequest.setId(id);
        mApiRequest.setPage(page);
        mApiRequest.setLimit(limit);
        mApiRequest.setMethodName(UrlConstants.REGULAR_RECORD);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, false);
    }

    /**
     * 项目记录  定期活期单个项目投资记录v200(Get)
     * get
     * @param taskResultPicker
     * @param TAG
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @author 郑昌奋      
     */
    public void getProjectInvestmentRecordsRequest(TaskResultPicker taskResultPicker, String TAG, int limit, int page, int id) {
        ProjectInvestmentRecordsRequest mApiRequest = new ProjectInvestmentRecordsRequest();
        mApiRequest.setLimit(limit);
        mApiRequest.setPage(page);
        mApiRequest.setProduct_id(id);
        mApiRequest.setMethodName(UrlConstants.PRODUCT_BUYLIST);
        ApiManager.executeTask(mContext, mApiRequest, taskResultPicker, true, TAG, false, true);
    }
}
