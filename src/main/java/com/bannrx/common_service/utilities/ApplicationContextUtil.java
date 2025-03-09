package com.bannrx.common_service.utilities;

import com.neonlab.common.services.ApplicationContextProvider;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class ApplicationContextUtil {

    @Autowired private ApplicationContextProvider applicationContextProvider;

}
