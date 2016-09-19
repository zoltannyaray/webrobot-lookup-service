package com.dayswideawake.webrobot.lookup.backend.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.backend.domain.Site;

@Service
public class ContentLoaderServiceImpl implements ContentLoaderService {

	@Override
	public String loadContent(Site site) {
		String content = "";
        try {
            content = Request.Get(site.getUrl().toString())
                    .execute()
                    .returnContent()
                    .asString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
	}

	
	
}
