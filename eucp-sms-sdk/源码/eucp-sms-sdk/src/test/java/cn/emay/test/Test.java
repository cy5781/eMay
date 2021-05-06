package cn.emay.test;

import java.io.IOException;

import cn.emay.sdk.client.SmsSDKClient;
import cn.emay.sdk.core.dto.sms.common.CustomSmsIdAndMobile;
import cn.emay.sdk.core.dto.sms.common.CustomSmsIdAndMobileAndContent;
import cn.emay.sdk.core.dto.sms.common.PersonalityParams;
import cn.emay.sdk.core.dto.sms.common.ResultModel;
import cn.emay.sdk.core.dto.sms.request.BalanceRequest;
import cn.emay.sdk.core.dto.sms.request.MoRequest;
import cn.emay.sdk.core.dto.sms.request.ReportRequest;
import cn.emay.sdk.core.dto.sms.request.RetrieveReportRequest;
import cn.emay.sdk.core.dto.sms.request.SmsBatchOnlyRequest;
import cn.emay.sdk.core.dto.sms.request.SmsBatchRequest;
import cn.emay.sdk.core.dto.sms.request.SmsPersonalityAllRequest;
import cn.emay.sdk.core.dto.sms.request.SmsPersonalityRequest;
import cn.emay.sdk.core.dto.sms.request.SmsSingleRequest;
import cn.emay.sdk.core.dto.sms.response.BalanceResponse;
import cn.emay.sdk.core.dto.sms.response.MoResponse;
import cn.emay.sdk.core.dto.sms.response.ReportResponse;
import cn.emay.sdk.core.dto.sms.response.RetrieveReportResponse;
import cn.emay.sdk.core.dto.sms.response.SmsResponse;
import cn.emay.sdk.util.exception.SDKParamsException;

public class Test {
	public static void main(String[] args) throws SDKParamsException, IOException {
		// sendSingleSms();
		// sendBatchOnlySms();
		// sendBatchSms();
		// sendPersonalitySms();
		// sendPersonalityAllSMS();
		// getMo();
		// getReport();
		// getBalance();
		retrieveReport();
	}

	public static void sendSingleSms() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		String mobile = "13800000000";
		String content = "短信内容";
		String customSmsId = "1";
		String extendedCode = "01";
		SmsSingleRequest request = new SmsSingleRequest(mobile, content, customSmsId, extendedCode, "");
		ResultModel<SmsResponse> result = client.sendSingleSms(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			SmsResponse response = result.getResult();
			System.out.println("sendSingleSms:" + response.toString());
		} else {
			System.out.println("请求失败");
		}
	}

	public static void sendBatchOnlySms() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		String[] mobiles = { "13800000000", "13800000001" };
		String content = "短信内容";
		String extendedCode = "01";
		SmsBatchOnlyRequest request = new SmsBatchOnlyRequest(mobiles, content, "", extendedCode);
		ResultModel<SmsResponse[]> result = client.sendBatchOnlySms(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			SmsResponse[] responses = result.getResult();
			for (SmsResponse response : responses) {
				System.out.println("sendBatchOnlySms:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}
	}

	public static void sendBatchSms() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		CustomSmsIdAndMobile[] cm = new CustomSmsIdAndMobile[2];
		cm[0] = new CustomSmsIdAndMobile("1", "13800000000");
		cm[1] = new CustomSmsIdAndMobile("2", "13800000001");
		String content = "短信内容";
		String extendedCode = "01";
		SmsBatchRequest request = new SmsBatchRequest(cm, content, "", extendedCode);
		ResultModel<SmsResponse[]> result = client.sendBatchSms(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			SmsResponse[] responses = result.getResult();
			for (SmsResponse response : responses) {
				System.out.println("sendBatchSms:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}
	}

	public static void sendPersonalitySms() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		String extendedCode = "01";

		CustomSmsIdAndMobileAndContent[] smses = new CustomSmsIdAndMobileAndContent[2];
		smses[0] = new CustomSmsIdAndMobileAndContent("1", "13800000000", "短信内容1");
		smses[1] = new CustomSmsIdAndMobileAndContent("2", "13800000001", "短信内容2");
		SmsPersonalityRequest request = new SmsPersonalityRequest(smses, "", extendedCode);
		ResultModel<SmsResponse[]> result = client.sendPersonalitySms(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			SmsResponse[] responses = result.getResult();
			for (SmsResponse response : responses) {
				System.out.println("sendPersonalitySms:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}
	}

	public static void sendPersonalityAllSMS() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");

		PersonalityParams[] smses = new PersonalityParams[2];
		smses[0] = new PersonalityParams("1", "13800000000", "短信内容1", "1", null);
		smses[1] = new PersonalityParams("2", "13800000001", "短信内容2", "2", null);
		SmsPersonalityAllRequest request = new SmsPersonalityAllRequest(smses);
		ResultModel<SmsResponse[]> result = client.sendPersonalityAllSMS(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			SmsResponse[] responses = result.getResult();
			for (SmsResponse response : responses) {
				System.out.println("sendPersonalityAllSMS:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}
	}

	public static void getReport() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		ReportRequest request = new ReportRequest();
		ResultModel<ReportResponse[]> result = client.getReport(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			ReportResponse[] responses = result.getResult();
			for (ReportResponse response : responses) {
				System.out.println("getReport:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}

	}

	public static void getMo() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		MoRequest request = new MoRequest();
		ResultModel<MoResponse[]> result = client.getMo(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			MoResponse[] responses = result.getResult();
			for (MoResponse response : responses) {
				System.out.println("getMo:" + response.toString());
			}
		} else {
			System.out.println("请求失败");
		}
	}

	public static void getBalance() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		BalanceRequest request = new BalanceRequest();
		ResultModel<BalanceResponse> result = client.getBalance(request);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			BalanceResponse response = result.getResult();
			System.out.println("getBalance:" + response.getBalance());
		} else {
			System.out.println("请求失败");
		}
	}

	public static void retrieveReport() throws SDKParamsException {
		SmsSDKClient client = new SmsSDKClient("127.0.0.1", 8999, "EUCP-EMY-SMS1-LPUE0", "749524E0CA7B76A2");
		String startTime = "20180120110000";
		String endTime = "20180120110500";
		String smsid = "15167713536420020356";
		RetrieveReportRequest reportRequest = new RetrieveReportRequest();
		reportRequest.setSmsId(smsid);
		reportRequest.setStartTime(startTime);
		reportRequest.setEndTime(endTime);
		ResultModel<RetrieveReportResponse> result = client.retrieveReport(reportRequest);
		if (result.getCode().equals("SUCCESS")) {
			System.out.println("请求成功");
			RetrieveReportResponse response = result.getResult();
			System.out.println("retrieveReport:" + response.getCode());
		} else {
			System.out.println("请求失败");
		}

	}

}
