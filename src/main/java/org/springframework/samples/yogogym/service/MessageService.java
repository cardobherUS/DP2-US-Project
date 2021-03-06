/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.yogogym.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class MessageService {

	private MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Transactional
	public Collection<Message> findAllMessages() throws DataAccessException
	{
		Collection<Message> allMessages = (Collection<Message>) this.messageRepository.findAll();
		
		return allMessages;
	}

	@Transactional
	public Collection<Message> findAllForumParentMessages(int forumId) throws DataAccessException {

		Collection<Message> messages = (Collection<Message>) this.messageRepository.findMessagesFromForumId(forumId);

		Collection<Message> res = new ArrayList<>();

		for (Message m : messages) {
			if (m.getIsParent()) {
				res.add(m);
			}
		}

		Comparator<Message> messageComparator = Comparator.comparing(Message::getCreatedAt).reversed();

		res = res.stream().sorted(messageComparator).collect(Collectors.toList());
		
		return res;
	}

	@Transactional
	public Message findMessageFromId(int messageId) throws DataAccessException {

		Message res = this.messageRepository.findMessageById(messageId);

		return res;
	}

	@Transactional
	public void saveMessage(Message message) throws DataAccessException {
		try {
			this.messageRepository.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
