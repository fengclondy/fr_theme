package com.fr.hailian.function;

import com.fr.hailian.core.Constants;
import com.fr.script.AbstractFunction;

@SuppressWarnings("serial")
public class QssNewsCompanyId extends AbstractFunction{

	@Override
	public Object run(Object[] arg0) {
		System.out.println(Constants.COMPANY_ID);
		return "('"+Constants.COMPANY_ID+"')";
	}

}
