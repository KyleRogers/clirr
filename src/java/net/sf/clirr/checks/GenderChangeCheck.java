//////////////////////////////////////////////////////////////////////////////
// Clirr: compares two versions of a java library for binary compatibility
// Copyright (C) 2003  Lars Kühne
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//////////////////////////////////////////////////////////////////////////////

package net.sf.clirr.checks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.clirr.*;
import net.sf.clirr.event.ApiDifference;
import net.sf.clirr.event.Severity;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassSet;

public class GenderChangeCheck
        extends AbstractDiffReporter
        implements ClassChangeCheck
{

    public GenderChangeCheck(ApiDiffDispatcher dispatcher)
    {
        super(dispatcher);
    }


    public void check(JavaClass baseLine, JavaClass current)
    {
        if (baseLine.isClass() != current.isClass())
        {
            getApiDiffDispatcher().fireDiff(new ApiDifference(
                    "Changed Gender of " + baseLine.getClassName(), Severity.ERROR)
            );
        }
    }

    // TODO: This should be a method in BCEL's ClassSet !!!
    private JavaClass findClass(String className, ClassSet classSet) {
        JavaClass[] classes = classSet.toArray();
        for (int i = 0; i < classes.length; i++) {
            JavaClass clazz = classes[i];
            if (clazz.getClassName().equals(className)){
                return clazz;
            }
        }
        throw new IllegalStateException();
    }

    private String[] intersectionClassNames(ClassSet setA, ClassSet setB)
    {
        String[] aNames = setA.getClassNames();
        String[] bNames = setB.getClassNames();
        Arrays.sort(aNames);
        Arrays.sort(bNames);

        List helper = new ArrayList();

        for (int i = 0; i < aNames.length; i++) {
            String aName = aNames[i];
            if (Arrays.binarySearch(bNames, aName) >= 0)
            {
                helper.add(aName);
            }
        }

        String[] retVal = new String[helper.size()];
        helper.toArray(retVal);
        return retVal;
    }
}
