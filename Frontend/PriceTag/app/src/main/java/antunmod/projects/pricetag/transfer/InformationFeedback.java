package antunmod.projects.pricetag.transfer;

import java.io.Serializable;

/**
 * Created by antun on 6/1/2018.
 */

public class InformationFeedback implements Serializable {


    private Integer id;

    private Integer priceId;

    private Short informationProviderUserId;

    private Short feedbackProviderUserId;

    /*
     * Feedback provided by user with feedbackProviderUserId. Can be POSITIVE(P) or
     * NEGATIVE (N).
     */
    private String feedback;

    public InformationFeedback() {
    }

    public InformationFeedback(Integer priceId, Short informationProviderUserId, Short feedbackProviderUserId, String feedback) {
        super();
        this.priceId = priceId;
        this.informationProviderUserId = informationProviderUserId;
        this.feedbackProviderUserId = feedbackProviderUserId;
        this.feedback = feedback;
    }

    public InformationFeedback(Integer priceId, Short feedbackProviderUserId, String feedback) {
        this.priceId = priceId;
        this.feedbackProviderUserId = feedbackProviderUserId;
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public Short getInformationProviderUserId() {
        return informationProviderUserId;
    }

    public Short getFeedbackProviderUserId() {
        return feedbackProviderUserId;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setInformationProviderUserId(Short informationProviderUserId) {
        this.informationProviderUserId = informationProviderUserId;
    }

}
