package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.GetPlaceRequest;
import com.nashss.se.citrusservice.activity.results.GetPlaceResult;

public class GetPlaceLambda extends LambdaActivityRunner<GetPlaceRequest, GetPlaceResult> implements RequestHandler<LambdaRequest<GetPlaceRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlaceRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetPlaceRequest.builder()
                                .withId(path.get("id"))
                                .build()),
                (request,serviceComponent) ->
                        serviceComponent.provideGetPlaceActivity().handleRequest(request));
    }
}
