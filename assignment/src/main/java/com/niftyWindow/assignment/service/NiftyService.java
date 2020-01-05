package com.niftyWindow.assignment.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.niftyWindow.assignment.dto.PrepareData;

@Service
public class NiftyService {

	public Object getNiftyData() {
		final String uri = "http://www.mocky.io/v2/5cdd110c3000007825e23470";

		RestTemplate restTemplate = new RestTemplate();
		String[] result = restTemplate.getForObject(uri, String[].class);
		String[] res = result;
		String[] string;
		List<String> userNames = new ArrayList<>();
		List<String> msgs = new ArrayList<>();
		List<String> fullMsg = new ArrayList<>();
		List<PrepareData> prepareDataList = new ArrayList<>();
		for (int i = 0; i < res.length; i++) {
			string = res[i].split(":");
			userNames.add(string[0]);
			msgs.add(string[1]);
			fullMsg.add(res[i]);
		}
		Set<String> unique = new HashSet<String>(userNames);
		TreeMap<Integer, String> usersContMap = new TreeMap<Integer, String>(Collections.reverseOrder());
		for (String key : unique) {
			usersContMap.put(Collections.frequency(userNames, key), key);
		}

		TreeMap<Integer, String> top5 = usersContMap.entrySet().stream().limit(5).collect(TreeMap::new,
				(m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

		Map<String, Integer> msgMap = new HashMap<>();
		for (int i = 0; i < fullMsg.size(); i++) {
			if (msgMap.get(fullMsg.get(i)) != null) {
				int val = msgMap.get(fullMsg.get(i));
				msgMap.put(fullMsg.get(i), val + 1);
			} else {
				msgMap.put(fullMsg.get(i), 1);
			}
		}
		List<String> topFrequencyMsg = new ArrayList<>();
		Collection<String> userName = null;

		userName = top5.values();
		int[] i = { 0 };
		userName.forEach(action -> {
			int[] max = { 0 };
			for (Entry<String, Integer> temp : msgMap.entrySet()) {
				String un = temp.getKey().split(":")[0];
				int count = temp.getValue();
				if (action.equalsIgnoreCase(un)) {
					if (max[0] < count) {
						topFrequencyMsg.add(temp.getKey() + ":" + temp.getValue());
						max[0] = count;
					}
				}
			}
			i[0]++;
		});

		String[] str = null;
		List<String> finalUsrNames = new ArrayList<>();
		List<String> finalMsg = new ArrayList<>();
		List<Integer> msgCount = new ArrayList<>();
		for (int i1 = 0; i1 < topFrequencyMsg.size(); i1++) {
			str = topFrequencyMsg.get(i1).split(":");
			finalUsrNames.add(str[0]);
			finalMsg.add(str[1]);
			int mCount = Integer.parseInt(str[2]);
			msgCount.add(mCount);
		}
		for (int k = 0; k < 8; k++) {
			PrepareData prepareData = new PrepareData();
			prepareData.setUserName(finalUsrNames.get(k));
			prepareData.setMsgCount(msgCount.get(k));
			prepareData.setMesssage(finalMsg.get(k));
			prepareDataList.add(prepareData);
		}
		Collections.sort(prepareDataList, Collections.reverseOrder());
		List<PrepareData> top5ElementsList = prepareDataList.stream().limit(5).collect(Collectors.toList());

		return top5ElementsList;
	}

}
