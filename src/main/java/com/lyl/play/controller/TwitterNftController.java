package com.lyl.play.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lyl.play.entity.TwitterNftRecord;
import com.lyl.play.mapper.TwitterNftMapper;
import com.lyl.play.service.TwitterNftService;
import com.lyl.play.service.impl.IpfsUploadService;
import com.lyl.play.service.impl.UploadService;
import com.lyl.play.vo.PageResult;
import com.lyl.play.vo.ResponseData;
import com.lyl.play.vo.req.AddressReq;
import com.lyl.play.vo.req.NewNftReq;
import com.lyl.play.vo.req.TwitterNftReq;
import com.lyl.play.vo.req.UpReq;
import com.lyl.play.vo.res.FileIpfsDto;
import com.lyl.play.vo.res.RecordResMes;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("twitter")
public class TwitterNftController {

    @Autowired
    private TwitterNftService twitterNftService;

    @Autowired
    private TwitterNftMapper twitterNftMapper;

    @Autowired
    private IpfsUploadService ipfsUploadService;

    @Autowired
    private UploadService uploadService;

    @ApiOperation("产品列表")
    @GetMapping("list")
    @ResponseBody
    public ResponseData<PageResult> list(TwitterNftReq req){
        if (StringUtils.isBlank(req.getMintUser()))
            return ResponseData.error("缺失参数");
        return ResponseData.success(twitterNftService.list(req));
    }

    @ApiOperation("账号绑定钱包")
    @PostMapping("bind")
    @ResponseBody
    public ResponseData bind(AddressReq req){
        if (StringUtils.isBlank(req.getAddress()) || StringUtils.isBlank(req.getMintUser()))
        return ResponseData.error("参数缺失");
        twitterNftService.bind(req);
        return ResponseData.success();
    }

    @ApiOperation("更新nft状态")
    @PostMapping("upstatus")
    @ResponseBody
    public ResponseData upStatus(UpReq req){
        if (req.getTokenId()==null)return ResponseData.error("缺失参数");
        int a=twitterNftService.upStatus(req);
        if (a<=0)return ResponseData.error("修改失败");
        return ResponseData.success();
    }

    @ApiOperation("接受")
    @PostMapping("accept")
    @ResponseBody
    public ResponseData<Integer> accept(UpReq req){
        if (StringUtils.isBlank(req.getAddress()) || req.getTokenId()==null)
            return ResponseData.error("缺少参数");
        return ResponseData.success(twitterNftService.accept(req));
    }

    @ApiOperation("铸造")
    @PostMapping("mint")
    @ResponseBody
    public ResponseData<List<RecordResMes>> mint(Long tokenId){
        if (tokenId==null)return ResponseData.error("缺少参数");
        return ResponseData.success(twitterNftService.mint(tokenId,2));
    }

    @ApiOperation("创建NFT")
    @PostMapping("createNft")
    @ResponseBody
    public ResponseData createNft(NewNftReq req){
        if (req==null) return ResponseData.error("缺少参数");
        twitterNftService.createNft(req);
        return ResponseData.success();
    }

    @ApiOperation("文件上传")
    @PostMapping("upload")
    @ResponseBody
    public ResponseData upload(MultipartHttpServletRequest request){
        Collection<MultipartFile> values = request.getFileMap().values();
        if (values.size()>1 || values.isEmpty())
            return ResponseData.error("只能上传单个文件");
        MultipartFile multipartFile = values.stream().findFirst().get();
        ResponseData upload = ipfsUploadService.upload(multipartFile);
        List<String> twitter = uploadService.upload(request,"twitter");
        JSONObject obj = JSONUtil.createObj();
        obj.put("fileUri",twitter.get(0));
        obj.put("ipfsUri",((FileIpfsDto) upload.getData()).getSourceIpfs());
        return ResponseData.success(obj);
    }

    @ApiOperation("导出excel")
    @PostMapping("exportExcel")
    @ResponseBody
    public ResponseData exportExcel(TwitterNftRecord t, HttpServletRequest request, HttpServletResponse response){
        List<TwitterNftRecord> twitterNftRecordList=twitterNftMapper.selectNoPage(t);
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook();
        return ResponseData.success();
    }



}
