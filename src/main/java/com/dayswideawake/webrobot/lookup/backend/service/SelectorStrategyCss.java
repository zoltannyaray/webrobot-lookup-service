package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;

@Component
public class SelectorStrategyCss implements SelectorStrategy<SelectorCss> {

	@Override
	public List<String> select(String content, SelectorCss selector) {
		Document document = Jsoup.parse(content);
        OutputSettings outputSettings = new OutputSettings();
        outputSettings.prettyPrint(false);
        document.outputSettings(outputSettings);
        Elements elements = document.select(selector.getCssSelector());
        return elements.stream()
        		.map(element -> element.outerHtml())
        		.collect(Collectors.toList());
	}

}
