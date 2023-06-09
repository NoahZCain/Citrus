package com.nashss.se.citrusservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.citrusservice.activity.requests.UpdateInterestsRequest;

import com.nashss.se.citrusservice.activity.results.UpdateInterestsResult;

import java.util.Collections;
import java.util.Set;

public class UpdateInterestsLambda extends LambdaActivityRunner<UpdateInterestsRequest, UpdateInterestsResult> implements RequestHandler<AuthenticatedLambdaRequest<UpdateInterestsRequest>,LambdaResponse> {
    /**
     * Handles a Lambda Function request
     *
     * @param input   The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateInterestsRequest> input, Context context) {
        System.out.println("NOAH " + input);
        return super.runActivity(
                () -> {
                    UpdateInterestsRequest unauthenticatedRequest = input.fromBody(UpdateInterestsRequest.class);
                    System.out.println("NOAH LINE 28 LAMBDA " + unauthenticatedRequest.toString());
                    String userIdFromPath = unauthenticatedRequest.getUserId();

                    return UpdateInterestsRequest.builder()
                            .withUserId(userIdFromPath)
                            .withUserInterests(unauthenticatedRequest.getUserInterests())
                            .build();
                },
                (request,serviceComponent) -> {
                    try{
                       return serviceComponent.provideUpdateInterestsActivity().handleRequest(request);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return  serviceComponent.provideUpdateInterestsActivity().handleRequest(request);
                }


        );
    }
}
