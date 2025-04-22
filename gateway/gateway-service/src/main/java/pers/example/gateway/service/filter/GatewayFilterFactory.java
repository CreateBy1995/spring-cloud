//package pers.example.gateway.service.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//
///**
// * @Author: dongcx
// * @CreateTime: 2024-08-28
// * @Description:
// */
//@Slf4j
//public class GatewayFilterFactory {
//    public static GatewayFilter getGatewayFilter(String filterName) {
//        if (filterName.equals(RequestTimeGatewayFilter.FILTER_NAME)){
//            return RequestTimeGatewayFilter.build();
//        }
//        if (filterName.equals(LogGatewayFilter.FILTER_NAME)){
//            return LogGatewayFilter.build();
//        }
//        return null;
//    }
//}
