import React from "react";

import {Form, Input, Upload, Button, Icon, Modal, DatePicker,Select} from "antd";
// import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {addOtherQualification} from "actions/OtherQualificationActions";

const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;


class OtherQualificationForm extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            visibleName: "none"
        }
    }

    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }

    reportTypeChange = (value) =>{
        this.setState({visibleName:value=="67"?"":"none"});
    }

    __handleOk() {
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                // console.log(this.refs.uploadFile);
                console.log(rows);
                Object.assign(rows, this.props.form.getFieldsValue());
                if (rows.report){
                    let fileList = [];
                    rows.report.map((item)=>{fileList.push(item.response.result)});
                    console.log(fileList)
                    console.log(JSON.stringify(fileList))
                    rows.report = JSON.stringify(fileList);
                }
                this.props.dispatch(addOtherQualification(rows))
                this.reset();
                this.props.close();
            } else {
                console.error('validate failed');
            }
        })
    }
    __handleClose(){
        this.reset();
        this.props.close();
    }
    normFile=(e)=> {
        console.log('Upload event:', e);
        if (Array.isArray(e)) {
            return e;
        }
        let result = "";
        if (e.file.response){
            result=  e.file.response.result;
            console.log(JSON.stringify(result) );
            // return JSON.stringify(result);
        }

        // return JSON.stringify(e.file.response) ;
        return e && e.fileList;
    }

    render() {

        const {dictionaryList} = this.props;
        let reportTypeOptins = [];
        if (dictionaryList){
            dictionaryList.forEach((item)=>{if(item.type=='REPORT_TYPE' && item.parentId == this.props.currentIndustry)reportTypeOptins.push(<Option key={item.id+""} value={item.id+""}>{item.value}</Option>)})
        }
        reportTypeOptins.push(<Option key="67" value="67">其他</Option>)
        const {getFieldDecorator} = this.props.form;
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
        return (
            <Modal title={"添加商品其它资质"}
                   visible={this.props.visible}
                   // onClose={this.props.close}
                   onClose={()=>this.__handleClose()}
                   onOk={()=>this.__handleOk()}
                   onCancel={()=>this.__handleClose()}
                   // onCancel={this.props.close}
                   okText={ "确定"}
                   cancelText={ "取消"}
            >
                <Form>
                    <FormItem
                        {...formItemLayout}
                        label="检测报告"

                    >
                        {getFieldDecorator('reportType', {})(

                            <Select placeholder="请选择检测报告"  onChange={this.reportTypeChange}>
                                {reportTypeOptins}

                            </Select>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="报告名称"
                        style={{display:this.state.visibleName}}
                    >
                        {getFieldDecorator('name', {})(
                            <Input placeholder="报告名称"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="检测有效时间"
                    >
                        {getFieldDecorator('expiryDate', {})(
                            <DatePicker />
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="检测机构"
                    >
                        {getFieldDecorator('detectionOrg', {})(
                            <Input placeholder="检测机构"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="上传检测报告"
                    >
                        {getFieldDecorator('report', {
                            valuePropName: 'fileList',
                            getValueFromEvent: this.normFile,
                            showUploadList:{ showPreviewIcon: true, showRemoveIcon: true },
                        })(
                            <Upload name="uploadFile" action="/incc/file/upload/1/4">
                                <Button>
                                    <Icon type="upload" /> 上传
                                </Button>
                            </Upload>

                        )}
                    </FormItem>

                </Form>
            </Modal>
        )
    }
}
;
OtherQualificationForm = createForm({})(OtherQualificationForm);
export default connect(
    state => {
        let {otherQualificationList, dictionaryList} = state;
        return {
            otherQualificationList, dictionaryList
        }
    }
)(OtherQualificationForm);