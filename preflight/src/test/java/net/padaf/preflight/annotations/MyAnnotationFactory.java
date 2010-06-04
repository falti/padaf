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
package net.padaf.preflight.annotations;

import java.util.List;

import org.apache.pdfbox.cos.COSDictionary;

import net.padaf.preflight.DocumentHandler;
import net.padaf.preflight.ValidationException;
import net.padaf.preflight.ValidationResult.ValidationError;
import net.padaf.preflight.annotation.AnnotationValidator;
import net.padaf.preflight.annotation.PDFAbAnnotationFactory;

public class MyAnnotationFactory extends PDFAbAnnotationFactory {

	public MyAnnotationFactory() {
	}

	public AnnotationValidator instantiateAnnotationValidator(COSDictionary annotDic,
			DocumentHandler handler, List<ValidationError> errors) {
		System.out.println("AnnotationValidatorFactory is overrided");
		return new MyAnnotationValidator(handler, annotDic);
	}
	
	public class MyAnnotationValidator extends AnnotationValidator {
		public static final String ERROR = "MyAnnotationValidator";
		public MyAnnotationValidator(DocumentHandler handler,
				COSDictionary annotDictionary) {
			super(handler, annotDictionary);
		}

		/* (non-Javadoc)
		 * @see net.padaf.preflight.annotation.AnnotationValidator#checkMandatoryFields(java.util.List)
		 */
		@Override
		protected boolean checkMandatoryFields(List<ValidationError> errors) {
			return false;
		}

		/* (non-Javadoc)
		 * @see net.padaf.preflight.annotation.AnnotationValidator#validate(java.util.List)
		 */
		@Override
		public boolean validate(List<ValidationError> errors)
		throws ValidationException {
			System.out.println("A Custom AnnotationValidator ");
			errors.add(new ValidationError(ERROR));
			return false;
		}
		
	}
}
