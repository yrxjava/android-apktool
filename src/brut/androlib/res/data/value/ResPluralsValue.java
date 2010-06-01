/*
 *  Copyright 2010 Ryszard Wiśniewski <brut.alll@gmail.com>.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package brut.androlib.res.data.value;

import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResResource;
import brut.util.Duo;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/**
 * @author Ryszard Wiśniewski <brut.alll@gmail.com>
 */
public class ResPluralsValue extends ResBagValue implements ResXmlSerializable {
    ResPluralsValue(ResReferenceValue parent,
            Duo<Integer, ResScalarValue>[] items) {
        super(parent);

        mItems = new String[6];
        for (int i = 0; i < items.length; i++) {
            mItems[items[i].m1 - BAG_KEY_PLURALS_START] =
                ((ResStringValue) items[i].m2).toResXmlFormat();
        }
    }

    public ResPluralsValue(ResReferenceValue parent, String[] items) {
        super(parent);
        mItems = items;
    }

    @Override
    public void serializeToXml(XmlSerializer serializer, ResResource res)
            throws IOException, AndrolibException {
        serializer.startTag(null, "plurals");
        serializer.attribute(null, "name", res.getResSpec().getName());
        for (int i = 0; i < mItems.length; i++) {
            String item = mItems[i];
            if (item == null) {
                continue;
            }
            serializer.startTag(null, "item");
            serializer.attribute(null, "quantity", QUANTITY_MAP[i]);
            serializer.text(item);
            serializer.endTag(null, "item");
        }
        serializer.endTag(null, "plurals");
    }


    private final String[] mItems;


    public static final int BAG_KEY_PLURALS_START = 0x01000004;
    public static final int BAG_KEY_PLURALS_END = 0x01000009;
    private static final String[] QUANTITY_MAP =
        new String[] {"other", "zero", "one", "two", "few", "many"};
}