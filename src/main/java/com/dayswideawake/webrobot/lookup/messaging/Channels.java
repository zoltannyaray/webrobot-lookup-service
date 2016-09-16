package com.dayswideawake.webrobot.lookup.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	String CHANNEL_INPUT_LOOKUP_JOBS = "lookup-jobs";
	String CHANNEL_OUTPUT_NEW_LOOKUPS = "new-lookups";
	
	@Input(CHANNEL_INPUT_LOOKUP_JOBS)
	SubscribableChannel lookupJobs();
	
	@Output(CHANNEL_OUTPUT_NEW_LOOKUPS)
	MessageChannel newLookups();
	
}
