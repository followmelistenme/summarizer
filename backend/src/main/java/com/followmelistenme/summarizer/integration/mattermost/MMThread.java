package com.followmelistenme.summarizer.integration.mattermost;

import java.util.List;
import java.util.Map;

public record MMThread(List<String> order, Map<String, MMPost> posts) {
}

