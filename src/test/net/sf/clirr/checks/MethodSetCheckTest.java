package net.sf.clirr.checks;

import net.sf.clirr.framework.ClassChangeCheck;
import net.sf.clirr.event.ApiDifference;
import net.sf.clirr.event.Severity;

/**
 * TODO: Docs.
 *
 * @author lkuehne
 */
public class MethodSetCheckTest extends AbstractCheckTestCase
{
    public void testMethodCheck()
    {
        ApiDifference[] expected = new ApiDifference[] {

            // method addition and removal
            new ApiDifference("Method 'public void removedMethod(java.lang.String)' has been removed in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public void removedMethod(java.lang.String)", null),
            new ApiDifference("Method 'public int getPriv2()' has been removed in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public int getPriv2()", null),
            new ApiDifference("Method 'public void printPriv()' has been removed in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public void printPriv()", null),

            // parameter type changes
            //new ApiDifference("Parameter type of method 'public void changeParamterType(java.lang.String)' has changed.",
            //        Severity.ERROR, "testlib.MethodsChange", "public void changeParamterType(java.lang.String)", null),
            //new ApiDifference("Parameter type of method 'public void weakenParamterType(java.lang.String)' has changed.",
            //        Severity.ERROR, "testlib.MethodsChange", "public void weakenParamterType(java.lang.String)", null),
            //new ApiDifference("Parameter type of method 'public void strengthenParamterType(java.lang.String)' has changed.",
            //        Severity.INFO, "testlib.MethodsChange", "public void strengthenParamterType(java.lang.Object)", null),

            // Constructor changes
            new ApiDifference("Parameter 1 of 'protected MethodsChange(int)' has changed it's type to java.lang.Integer in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "protected MethodsChange(int)", null),
//            new ApiDifference("Constructor 'protected MethodsChange(int, boolean)' has been added in testlib.MethodsChange",
//                    Severity.INFO, "testlib.MethodsChange", "public MethodsChange(int, boolean)", null),

            // return type changes
            new ApiDifference("Return type of Method 'public java.lang.Number getPrivAsNumber()' has been changed to java.lang.Integer in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public java.lang.Number getPrivAsNumber()", null),
            // TODO: INFO if method is final
            new ApiDifference("Return type of Method 'public java.lang.Integer getPrivAsInteger()' has been changed to java.lang.Number in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public java.lang.Integer getPrivAsInteger()", null),

            // parameter list changes
            // Note: This is the current behaviour, not necessarily the spec of the desired behaviour
            // TODO: need to check assignability of types (and check if method or class is final?)
            new ApiDifference("Parameter 1 of 'public void strengthenParamType(java.lang.Object)' has changed it's type to java.lang.String in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public void strengthenParamType(java.lang.Object)", null),
            new ApiDifference("Parameter 1 of 'public void weakenParamType(java.lang.String)' has changed it's type to java.lang.Object in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public void weakenParamType(java.lang.String)", null),
            new ApiDifference("Parameter 1 of 'public void changeParamType(java.lang.String)' has changed it's type to java.lang.Integer in testlib.MethodsChange",
                    Severity.ERROR, "testlib.MethodsChange", "public void changeParamType(java.lang.String)", null),

            // declared exceptions
            // TODO
        };
        verify(expected);
    }

    protected final ClassChangeCheck createCheck(TestDiffListener tdl)
    {
        return new MethodSetCheck(tdl);
    }
}
