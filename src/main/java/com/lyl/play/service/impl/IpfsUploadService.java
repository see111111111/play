package com.lyl.play.service.impl;

import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.lyl.play.common.BusinessException;
import com.lyl.play.vo.ResponseData;
import com.lyl.play.vo.res.FileIpfsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class IpfsUploadService {

    @Value("${pinata.api-key}")
    private String pinataApiKey;
    @Value("${pinata.secret-api-key}")
    private String pinataSecretApiKey;

    private static final String file_upload_url = "https://api.pinata.cloud/pinning/pinFileToIPFS";
    private static final String json_upload_uri = "https://api.pinata.cloud/pinning/pinJSONToIPFS";

    /**
     * 上传文件到ipfs
     *
     * @param file
     * @return
     */
    public ResponseData upload(MultipartFile file) {
        FileIpfsDto fileIpfsDto = new FileIpfsDto();
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        headers.add("pinata_api_key", pinataApiKey);
        headers.add("pinata_secret_api_key", pinataSecretApiKey);
        final String originalFilename = file.getOriginalFilename();
        ByteArrayResource fileAsResource = null;
        try {
            fileAsResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return originalFilename;
                }

                @Override
                public long contentLength() {
                    return file.getSize();
                }
            };
        } catch (IOException e) {
            log.error("pinata文件上传失败：{}", e.getMessage());
            throw new BusinessException("pinata文件上传失败：" + e.getMessage());
        }
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileAsResource);
        String fileName = originalFilename.substring(0, originalFilename.indexOf("."));
        form.add("pinataMetadata", "{\"name\":\"" + fileName + "_source" + "\"}");
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        String s = restTemplate.postForObject(file_upload_url, files, String.class);
        JsonObject jsonObject = new Gson().fromJson(s, JsonObject.class);
        fileIpfsDto.setName(fileName);
        fileIpfsDto.setSourceIpfs(jsonObject.get("IpfsHash").getAsString());
        return ResponseData.success(fileIpfsDto);
    }


    /**
     * json信息上传ipfs
     *
     * @param jsonObject
     * @return
     */
    public String uploadIpfsForJson(JSONObject jsonObject) {
        try {
            JSONObject data = new JSONObject();
            //文件信息
            JSONObject pinataMetadata = new JSONObject();
            pinataMetadata.put("name", jsonObject.get("fileName"));
            JSONObject keyvalues = new JSONObject();
            keyvalues.put("id", jsonObject.get("tokenId"));
            pinataMetadata.put("keyvalues", keyvalues);
            //数据信息
            JSONObject pinataContent = new JSONObject();
            pinataContent.put("name", jsonObject.get("name"));
            pinataContent.put("description", jsonObject.get("description"));
            pinataContent.put("image", "https://ipfs.io/ipfs/" + jsonObject.get("image"));
            data.put("pinataMetadata", pinataMetadata);
            data.put("pinataContent", pinataContent);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("pinata_api_key", pinataApiKey);
            headers.add("pinata_secret_api_key", pinataSecretApiKey);
            RestTemplate rest = new RestTemplate();
            HttpEntity<String> entity = new HttpEntity<>(data.toString(), headers);
            String body = rest.exchange(json_upload_uri, HttpMethod.POST, entity, String.class).getBody();
            if (body.contains("IpfsHash")) {
                JsonObject resp = new Gson().fromJson(body, JsonObject.class);
                return resp.get("IpfsHash").getAsString();
            }
        } catch (Exception e) {
            log.error("pinata文件信息转换异常，异常信息：{}", e);
            throw new BusinessException("pinata文件信息转换异常：" + e.getMessage());
        }
        return "";
    }

}
