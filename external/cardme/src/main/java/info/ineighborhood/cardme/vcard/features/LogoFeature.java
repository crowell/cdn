/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package info.ineighborhood.cardme.vcard.features;

import info.ineighborhood.cardme.vcard.EncodingType;
import info.ineighborhood.cardme.vcard.types.media.ImageMediaType;
import info.ineighborhood.cardme.vcard.types.parameters.LogoParameterType;
import java.net.URI;

/**
 * Copyright (c) 2004, Neighborhood Technologies
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * Neither the name of Neighborhood Technologies nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Feb 4, 2010
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.5.3 LOGO Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> LOGO</li>
 * 	<li><b>Type purpose:</b> To specify a graphic image of a logo associated with the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> The encoding MUST be reset to "b" using the ENCODING parameter in order to specify inline, encoded binary data. If the value is referenced by a URI value, then the default encoding of 8bit is used and no explicit ENCODING parameter is needed.</li>
 * 	<li><b>Type value:</b> A single value. The default is binary value. It can also be reset to uri value. The uri value can be used to specify a value outside of this MIME entity.</li>
 * 	<li><b>Type special note:</b> The type can include the type parameter "TYPE" to specify the graphic image format type. The TYPE parameter values MUST be one of the IANA registered image formats or a non-standard image format.</li>
 * </ul>
 * </p>
 */
public interface LogoFeature extends TypeTools, TypeData {
	
	/**
	 * <p>Returns the logo as an array of bytes.</p>
	 *
	 * @return byte[]
	 */
	public byte[] getLogo();
	
	/**
	 * <p>Returns the logo URI.</p>
	 *
	 * @return {@link URI}
	 */
	public URI getLogoURI();
	
	/**
	 * <p>Returns the encoding type of this logo.</p>
	 *
	 * @return {@link EncodingType}
	 */
	public EncodingType getEncodingType();
	
	/**
	 * <p>Returns the parameter type of this logo.</p>
	 *
	 * @return {@link LogoParameterType}
	 */
	public LogoParameterType getLogoParameterType();
	
	/**
	 * <p>Returns the image format for this logo.</p>
	 *
	 * @return {@link ImageMediaType}
	 */
	public ImageMediaType getImageMediaType();
	
	/**
	 * <p>Sets the logo.</p>
	 *
	 * @param logoBytes
	 */
	public void setLogo(byte[] logoBytes);
	
	/**
	 * <p>Sets the URI.</p>
	 *
	 * @param logoUri
	 */
	public void setLogoURI(URI logoUri);
	
	/**
	 * <p>Sets the encoding type.</p>
	 *
	 * @param encodingType
	 */
	public void setEncodingType(EncodingType encodingType);
	
	/**
	 * <p>Sets the parameter type.</p>
	 *
	 * @param logoParameterType
	 */
	public void setLogoParameterType(LogoParameterType logoParameterType);
	
	/**
	 * <p>Sets the image format type.</p>
	 *
	 * @param imageMediaType
	 */
	public void setImageMediaType(ImageMediaType imageMediaType);
	
	/**
	 * <p>Returns true if a logo exists, URI or in-line data.</p>
	 *
	 * @return boolean
	 */
	public boolean hasLogo();
	
	/**
	 * <p>Returns true if the logo has a URI.</p>
	 *
	 * @return boolean
	 */
	public boolean isURI();
	
	/**
	 * <p>Returns true if the logo has in-line data.</p>
	 *
	 * @return boolean
	 */
	public boolean isInline();
	
	/**
	 * <p>Returns true if this logo has a parameter type.</p>
	 *
	 * @return boolean
	 */
	public boolean hasLogoParameterType();
	
	/**
	 * <p>Returns true if this logo has an image format type.</p>
	 *
	 * @return boolean
	 */
	public boolean hasImageMediaType();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link LogoFeature}
	 */
	public LogoFeature clone();
}
