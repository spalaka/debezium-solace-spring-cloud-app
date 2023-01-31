/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.cdc.debezium.source.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import java.util.function.Function;


@SpringBootApplication
@Import({ org.springframework.cloud.fn.supplier.cdc.CdcSupplierConfiguration.class })
public class CdcDebeziumSourceSolaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcDebeziumSourceSolaceApplication.class, args);
	}
	public static int count = 0;

	@Bean
	public Function<Message<String>, String> solaceConsumer() {

		return reading -> {
			count = count+1;
			if(count>2)throw new RuntimeException ( "Message Failed" );
			return reading.getPayload ().toUpperCase ();
		};

	}

}
