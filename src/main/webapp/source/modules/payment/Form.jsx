import React from "react";
import {connect} from 'react-redux';
import {savePayment, getPayment} from "actions/PaymentActions";
import {allDistributorOptions} from "actions/DistributorActions";
import {allCommodityOptions} from "actions/CommodityActions";
import SubPage from 'modules/common/SubPage';
import moment from 'moment';
import {Form, Input, InputNumber, Checkbox, Select, Button, DatePicker} from "antd";
import {showModalDialog} from "actions/CommonAction";
const FormItem = Form.Item;
const CheckboxGroup = Checkbox.Group;
const createForm = Form.create;
const Option = Select.Option;

class PaymentAdd extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(allDistributorOptions());
        this.props.dispatch(allCommodityOptions());
        this.props.dispatch(getPayment(this.props.params.id));
    }

    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            this.props.dispatch(getPayment(statusId));
            return false;
        }
        return true;
    }
    reset() {
        this.props.form.resetFields();
    }

    savePayment() {
        E.addOneTimeEventListener("topaymentlist",  (e) => {
            this.props.history.push('/manager/payment/list');
        })
        let rows = this.props.form.getFieldsValue();
        this.props.dispatch(savePayment(rows));
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedPayment} = this.props;
        const {selectedPayment:{id, transferDate= "", payer="", fee=0, memo="", commodityId="", distributorId=""}} = this.props;
        const commodity = selectedPayment?selectedPayment.commodity:null;
        const distributor = selectedPayment?selectedPayment.distributor:null;
        console.log("commodity",commodity)
        const defaultTransferDate = transferDate?moment(transferDate):moment();
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 5},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 14,
                    offset: 6,
                },
            },
        };

        const {distributorOptions} = this.props;
        let distributorOptionsObj = [];
        distributorOptionsObj = distributorOptions.map((item) => {
            return <Option key={item.ID}>{item.NAME}</Option>;
        });
        const {commodityOptions} = this.props;
        let commodityOptionsObj = [];
        commodityOptionsObj = commodityOptions.map((item) => {
            return <Option key={item.ID}>{item.NAME}</Option>;
        });
        return (
            <SubPage breadcrumb="/后台管理/登记标识费用/登记标识费用">
			<Form>
                {getFieldDecorator('id', {
                    initialValue: id,
                })(
                    <Input type="hidden"/>
                )}
				<FormItem

                    {...formItemLayout}
                    label="所属分销商"
                >{(commodity != null && commodity.status > 3) ?
                    getFieldDecorator('distributorId', {
                        initialValue: distributorId,
                    })(
                    <div><Input type="hidden"/>{distributor.name}</div>
                    )
                    :
                getFieldDecorator('distributorId', {initialValue: distributorId,})(
                    <Select >
                    {distributorOptionsObj}
                    </Select>
                    )
                }
				</FormItem>
                <FormItem
                   {...formItemLayout}
					label="商品名称"
				>
                    {(commodity != null && commodity.status > 3) ?
                        getFieldDecorator('commodityId', {
                            initialValue: commodityId,
                        })(
                            <div><Input type="hidden"/>{commodity.name}</div>
                        ):
                        getFieldDecorator('commodityId', {initialValue: commodityId,})(
                        <Select >
                            {commodityOptionsObj}
                        </Select>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="缴费时间"

				>
                    {getFieldDecorator('transferDate', {initialValue: defaultTransferDate,})(
                        <DatePicker />
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="缴费人"
				>
                    {getFieldDecorator('payer', {initialValue: payer,})(
						<Input placeholder="缴费人l"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="标识费用"
				>
                    {getFieldDecorator('fee', {initialValue: fee,})(
						<InputNumber placeholder="标识费用"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="备注"
				>
                    {getFieldDecorator('memo', {initialValue: memo,})(
						<Input placeholder="备注"/>
                    )}
				</FormItem>

                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large"
                            onClick={() => this.savePayment()}>确定</Button>

                    <Button type="primary" htmlType="reset" size="large" onClick={() => this.reset()}
                            style={{marginLeft: 8}}>重置</Button>
                </FormItem>
			</Form>
            </SubPage>
        )
    }
}
;
PaymentAdd = createForm()(PaymentAdd);
export default  connect(
    state => {
        let {distributorOptions,commodityOptions, selectedPayment} = state;
        return {
            distributorOptions,commodityOptions, selectedPayment
        }
    }
)(PaymentAdd);