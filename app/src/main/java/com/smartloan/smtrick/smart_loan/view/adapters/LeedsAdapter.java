package com.smartloan.smtrick.smart_loan.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.smartloan.smtrick.smart_loan.R;
import com.smartloan.smtrick.smart_loan.models.LeedsModel;
import com.smartloan.smtrick.smart_loan.utilities.Utility;

import java.util.ArrayList;
import java.util.HashSet;

import static com.smartloan.smtrick.smart_loan.constants.Constant.DAY_DATE_FORMATE;
import static com.smartloan.smtrick.smart_loan.constants.Constant.LEED_DATE_FORMATE;

@SuppressWarnings({"WeakerAccess", "unused"})
public class LeedsAdapter extends ArrayAdapter<LeedsModel> {
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    Context context;
    private ArrayList<LeedsModel> leedsModelList;

    public LeedsAdapter(Context context, ArrayList<LeedsModel> leedsModelList) {
        super(context, 0, leedsModelList);
        this.context = context;
        this.leedsModelList = leedsModelList;
    }

    private LeedsModel getModel(int position) {
        return leedsModelList.get(getCount() - 1 - position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LeedsModel leedsModel = getModel(position);
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            viewHolder.textViewAppliedLoanValue = cell.findViewById(R.id.text_view_applied_loan_value);
            viewHolder.textviewApprovedValue = cell.findViewById(R.id.textview_approved_value);
            viewHolder.textviewDateLabel = cell.findViewById(R.id.textview_date_label);
            viewHolder.textviewTimeValue = cell.findViewById(R.id.textview_time_value);
            viewHolder.textViewCustomerName = cell.findViewById(R.id.text_view_customer_name);
            viewHolder.textViewStatusValue = cell.findViewById(R.id.text_view_status_value);
            viewHolder.textloantype = cell.findViewById(R.id.textloantype);
            viewHolder.textbankname = cell.findViewById(R.id.textbankname);
            viewHolder.payoutammount = cell.findViewById(R.id.payoutammount);
            //detail views
            viewHolder.textviewDetailCustomerName = cell.findViewById(R.id.textview_customer_name);
            viewHolder.textviewDetailLoanTypeStatus = cell.findViewById(R.id.textview_loan_type);
            viewHolder.textviewDetailMobilleNumberValue = cell.findViewById(R.id.textview_mobille_number_value);
            viewHolder.textviewDetailEmailidValue = cell.findViewById(R.id.textview_emailid_value);
            viewHolder.txtinfoaddress = cell.findViewById(R.id.txtinfoaddress);
            viewHolder.txtDetailloantypevalue = cell.findViewById(R.id.txtloantypevalue);
            viewHolder.txtDetailbankvalue = cell.findViewById(R.id.txtbankvalue);
            viewHolder.txtDetailpayoutvalue = cell.findViewById(R.id.txtpayoutvalue);
            viewHolder.txtagentvalue = cell.findViewById(R.id.txtagentvalue);
            viewHolder.txtapprovedloanvalue = cell.findViewById(R.id.txtapprovedloanvalue);
            viewHolder.contentRequestBtn = cell.findViewById(R.id.title_request);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == leedsModel)
            return cell;
        if (!Utility.isEmptyOrNull(leedsModel.getExpectedLoanAmount()))
            viewHolder.textViewAppliedLoanValue.setText(leedsModel.getExpectedLoanAmount());
        else
            viewHolder.textViewAppliedLoanValue.setText(getString(R.string.na));
        if (!Utility.isEmptyOrNull(leedsModel.getApprovedLoan())) {
            viewHolder.textviewApprovedValue.setText(leedsModel.getApprovedLoan());
            viewHolder.txtapprovedloanvalue.setText(leedsModel.getApprovedLoan());
        } else {
            viewHolder.textviewApprovedValue.setText(getString(R.string.na));
            viewHolder.txtapprovedloanvalue.setText(getString(R.string.na));
        }
        if (!Utility.isEmptyOrNull(leedsModel.getCustomerName())) {
            viewHolder.textViewCustomerName.setText(leedsModel.getCustomerName());
            viewHolder.textviewDetailCustomerName.setText(leedsModel.getCustomerName());
        } else {
            viewHolder.textViewCustomerName.setText(getString(R.string.na));
            viewHolder.textviewDetailCustomerName.setText(getString(R.string.na));
        }
        if (!Utility.isEmptyOrNull(leedsModel.getStatus()))
            viewHolder.textViewStatusValue.setText(leedsModel.getStatus());
        else
            viewHolder.textViewStatusValue.setText(getString(R.string.na));
        if (!Utility.isEmptyOrNull(leedsModel.getLoanType())) {
            viewHolder.textloantype.setText(leedsModel.getLoanType());
            viewHolder.textviewDetailLoanTypeStatus.setText(leedsModel.getLoanType());
            viewHolder.txtDetailloantypevalue.setText(leedsModel.getLoanType());
        } else {
            viewHolder.textloantype.setText(getString(R.string.na));
            viewHolder.textviewDetailLoanTypeStatus.setText(getString(R.string.na));
            viewHolder.txtDetailloantypevalue.setText(getString(R.string.na));
        }
        if (!Utility.isEmptyOrNull(leedsModel.getBankName())) {
            viewHolder.textbankname.setText(leedsModel.getBankName());
            viewHolder.txtDetailbankvalue.setText(leedsModel.getBankName());
        } else {
            viewHolder.textbankname.setText(getString(R.string.na));
            viewHolder.txtDetailbankvalue.setText(getString(R.string.na));
        }
        if (!Utility.isEmptyOrNull(leedsModel.getPayout())) {
            viewHolder.payoutammount.setText(leedsModel.getPayout());
            viewHolder.txtDetailpayoutvalue.setText(leedsModel.getPayout());
        } else {
            viewHolder.payoutammount.setText(getString(R.string.na));
            viewHolder.txtDetailpayoutvalue.setText(getString(R.string.na));
        }
        if (!Utility.isEmptyOrNull(leedsModel.getMobileNumber()))
            viewHolder.textviewDetailMobilleNumberValue.setText(leedsModel.getMobileNumber());
        else
            viewHolder.textviewDetailMobilleNumberValue.setText(getString(R.string.na));

        if (!Utility.isEmptyOrNull(leedsModel.getEmail()))
            viewHolder.textviewDetailEmailidValue.setText(leedsModel.getEmail());
        else
            viewHolder.textviewDetailEmailidValue.setText(getString(R.string.na));

        if (!Utility.isEmptyOrNull(leedsModel.getAddress()))
            viewHolder.txtinfoaddress.setText(leedsModel.getAddress());
        else
            viewHolder.txtinfoaddress.setText(getString(R.string.na));

        if (!Utility.isEmptyOrNull(leedsModel.getAgentName()))
            viewHolder.txtagentvalue.setText(leedsModel.getAgentName());
        else
            viewHolder.txtagentvalue.setText(getString(R.string.na));

        if (leedsModel.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(leedsModel.getRequestBtnClickListener());
        } else {
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        if (leedsModel.getCreatedDateTimeLong() > 0) {
            viewHolder.textviewDateLabel.setText(Utility.convertMilliSecondsToFormatedDate(leedsModel.getCreatedDateTimeLong(), DAY_DATE_FORMATE));
            viewHolder.textviewTimeValue.setText(Utility.convertMilliSecondsToFormatedDate(leedsModel.getCreatedDateTimeLong(), LEED_DATE_FORMATE));
        } else {
            viewHolder.textviewDateLabel.setText(getString(R.string.na));
            viewHolder.textviewTimeValue.setText(getString(R.string.na));
        }
        return cell;
    }

    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    private String getString(int id) {
        return context.getString(id);
    }

    // View lookup cache
    private static class ViewHolder {
        TextView textViewAppliedLoan;
        TextView textViewAppliedLoanValue;
        TextView contentRequestBtn;
        TextView textviewApproved;
        TextView textviewApprovedValue;

        TextView textviewDateLabel;
        TextView textviewTimeValue;
        TextView textViewCustomerName;
        TextView textViewStatus;
        TextView textViewStatusValue;
        TextView textloantypetxt;
        TextView textloantype;
        TextView textbanknametxt;
        TextView textbankname;
        TextView layoutstickerbottom;
        TextView textpayouttxt;
        TextView payoutammount;

        TextView contentAvatarTitle;
        TextView textviewDetailCustomerName;
        TextView textviewDetailAppliedText;
        TextView textviewDetailLoanType;
        TextView content_from_badge;
        TextView textviewDetailMobilleNumber;
        TextView textviewDetailMobilleNumberValue;
        TextView textviewDetailEmailid;
        TextView textviewDetailEmailidValue;
        TextView textDetailaddress;
        TextView txtinfoaddress;
        TextView txtDetailloantype;
        TextView txtDetailloantypevalue;
        TextView txtDetailbank;
        TextView txtDetailbankvalue;
        TextView txDetailtpayout;
        TextView txtDetailpayoutvalue;
        TextView txtagent;
        TextView txtagentvalue;
        TextView txtapprovedloan;
        TextView txtapprovedloanvalue;
        TextView title_request;
        TextView textviewDetailLoanTypeStatus;
        ImageView imageviewAvatar;
    }

    public void reload(ArrayList<LeedsModel> leedsModels) {
        leedsModelList.clear();
        leedsModelList.addAll(leedsModels);
        notifyDataSetChanged();
    }
}
