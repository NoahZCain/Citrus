package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.SearchForPlaceRequest;
import com.nashss.se.citrusservice.activity.results.SearchForPlaceResult;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchForPlaceLambda extends LambdaActivityRunner<SearchForPlaceRequest, SearchForPlaceResult> implements RequestHandler<LambdaRequest<SearchForPlaceRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    private final Logger log = LogManager.getLogger();
    
    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchForPlaceRequest> input, Context context) {
        log.info("HandleRequest");
        return super.runActivity(
            () -> {
                Map<String, String> queryParameters = input.getQueryStringParameters();
            String placeName = queryParameters.get("placeName");
            if (placeName != null && placeName.startsWith("placeName=")) {
                placeName = placeName.substring("placeName=".length());
            }
            return SearchForPlaceRequest.builder()
                .withplaceName(placeName)
                .build();
            },
                (request,serviceComponent) ->
                        serviceComponent.provideSearchForPlaceActivity().handleRequest(request)
        );
    }
}
