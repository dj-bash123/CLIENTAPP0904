package in.co.snapqa.clientapp0903.interfaces;

import in.co.snapqa.clientapp0903.models.AcceptDealRequest;
import in.co.snapqa.clientapp0903.models.AcceptedDealResponse;
import in.co.snapqa.clientapp0903.models.AuthRequest;
import in.co.snapqa.clientapp0903.models.ChangePasswordRequest;
import in.co.snapqa.clientapp0903.models.ChangePasswordResponse;
import in.co.snapqa.clientapp0903.models.ForgotPasswordRequest;
import in.co.snapqa.clientapp0903.models.ForgotPasswordResponse;
import in.co.snapqa.clientapp0903.models.NewDealResponses;
import in.co.snapqa.clientapp0903.models.OTPResponse;
import in.co.snapqa.clientapp0903.models.PaymentDetailsRequest;
import in.co.snapqa.clientapp0903.models.PaymentDetailsResponse;
import in.co.snapqa.clientapp0903.models.SendOTP;
import in.co.snapqa.clientapp0903.models.SignUpRequest;
import in.co.snapqa.clientapp0903.models.SignUpResponse;
import in.co.snapqa.clientapp0903.models.SubjectAddRequest;
import in.co.snapqa.clientapp0903.models.SubjectAddResponse;
import in.co.snapqa.clientapp0903.models.TokenRequest;
import in.co.snapqa.clientapp0903.models.TokenResponse;
import in.co.snapqa.clientapp0903.models.UserProfileResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by OLA on 09/03/17.
 */

public interface API {


    //String app_Key = "oEBg-3z4hgcv5X8sk_AYdVDiqpGCN02G3cFRjCK0er6MWhuSHAQDRT3TuJKxzOI__2H3D_gZZWeMJsns92zEm4LlksdilXYePbiFZRc1OLZxZd1DmSQOlmM-MIhDrXOqefgIVJX_deqP0QfRoBZ-PtlqpCtZFRqem1kl_J2Vra8="

    @POST("users/signin")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

    //Post request for Signing Up
    @POST("users/signup")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("deals/viewDealsUpComing")
    Call<NewDealResponses> newDeals(@Body AuthRequest authRequest);

    @POST("users/addSubjects")
    Call<SubjectAddResponse> addSubject(@Body SubjectAddRequest subjectAddRequest);

    @POST("deals/acceptedDeal")
    Call<AcceptedDealResponse> acceptedDeal(@Body AcceptDealRequest acceptDealRequest);

    @POST("deals/liveDeals")
    Call<NewDealResponses> liveDeals(@Body AuthRequest authRequest);

    @POST("deals/homeWorkDeals")
    Call<NewDealResponses> hwDeals(@Body AuthRequest authRequest);

    @POST("users/userProfile")
    Call<UserProfileResponse> userProfile(@Body AuthRequest authRequest);

    @POST("users/paymentDetails")
    Call<PaymentDetailsResponse> paymentDetails(@Body PaymentDetailsRequest paymentDetailsRequest);

    @POST("users/forgotPassword")
    Call<ForgotPasswordResponse> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @POST("users/changePassword")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("users/otpVerified")
    Call<OTPResponse> otpVerified(@Body SendOTP sendOTP);

}