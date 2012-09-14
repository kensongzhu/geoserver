/* Copyright (c) 2001 - 2007 TOPP - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, availible at the root
 * application directory.
 */
package org.geoserver.csw.response;

import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import net.opengis.cat.csw20.RequestBaseType;

import org.geoserver.config.GeoServer;
import org.geoserver.csw.CSWInfo;
import org.geoserver.csw.records.CSWRecordTypes;
import org.geoserver.platform.ServiceException;
import org.geotools.csw.CSW;

/**
 * Encodes responses with CSW Dublin Core records 
 * 
 * @author Andrea Aime - GeoSolutions
 */
public class CSWRecordsResponse extends AbstractRecordsResponse {

    public CSWRecordsResponse(GeoServer gs) {
        super(CSWRecordTypes.RECORD, CSW.NAMESPACE, gs);
    }

    protected void transformResponse(OutputStream output, CSWRecordsResult result,
            RequestBaseType request, CSWInfo csw) {
        CSWRecordTransformer transformer = new CSWRecordTransformer(request,
                csw.isCanonicalSchemaLocation());
        try {
            transformer.transform(result, output);
        } catch (TransformerException e) {
            throw new ServiceException(e);
        }
    }

}
