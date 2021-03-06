/*******************************************************************************
 * Copyright 2010 Atos Worldline SAS
 * 
 * Licensed by Atos Worldline SAS under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Atos Worldline SAS licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.padaf.preflight.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

import org.apache.commons.io.IOUtils;

public class ByteArrayDataSource implements DataSource {
  private ByteArrayOutputStream data;
  private String type = null;
  private String name = null;

  public ByteArrayDataSource(InputStream is) throws IOException {
    data = new ByteArrayOutputStream();
    IOUtils.copyLarge(is, data);
    IOUtils.closeQuietly(is);
  }

  public String getContentType() {
    return this.type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(data.toByteArray());
  }

  public String getName() {
    return this.name;
  }

  public OutputStream getOutputStream() throws IOException {
    this.data = new ByteArrayOutputStream();
    return data;
  }
}
