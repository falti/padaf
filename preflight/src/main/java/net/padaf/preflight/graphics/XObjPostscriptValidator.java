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
package net.padaf.preflight.graphics;

import java.util.List;

import net.padaf.preflight.DocumentHandler;
import net.padaf.preflight.ValidationException;
import net.padaf.preflight.ValidationResult.ValidationError;

import org.apache.pdfbox.cos.COSStream;

public class XObjPostscriptValidator extends AbstractXObjValidator {

  public XObjPostscriptValidator(DocumentHandler handler, COSStream xobj) {
    super(handler, xobj);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.awl.edoc.pdfa.validation.graphics.AbstractXObjValidator#validate()
   */
  @Override
  public List<ValidationError> validate() throws ValidationException {
    return super.validate();
  }

  /*
   * (non-Javadoc)
   * 
   * @seenet.awl.edoc.pdfa.validation.graphics.AbstractXObjValidator#
   * checkMandatoryFields(java.util.List)
   */
  @Override
  protected boolean checkMandatoryFields(List<ValidationError> result) {
    // PostScript XObjects are forbidden. Whatever the result of this function,
    // the validation will fail
    return true;
  }

}
