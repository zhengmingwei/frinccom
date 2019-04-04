import React from "react";

import {Form, Input, Upload, Select, Icon} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {saveDistributor} from "actions/DistributorActions";
import LimitUpload from 'modules/common/LimitUpload';
const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;

class BrandForm extends React.Component {

    constructor(props) {
        super(props);
    }

    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }

    saveUser() {
        let rows = this.props.form.getFieldsValue();

    }
    handleSubmit() {
        console.log(this)

        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                console.log(this.props.form.getFieldsValue());
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                if (rows.file){
                    const file = rows.file[0];
                    rows.file = JSON.stringify(file.response.result);
                }
                this.props.dispatch(saveDistributor(rows))
            } else {
                console.error('validate failed');
            }
        })
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

              <div>
                    <FormItem
                        {...formItemLayout}
                        label="品牌名称"

                    >
                        {getFieldDecorator('brand.name', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "品牌名称.",
                            }],})(

                            <Input placeholder="品牌名称"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="品牌注册号"
                    >
                        {getFieldDecorator('brand.code', {})(
                            <Input placeholder="品牌注册号"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="品牌权利人"
                    >
                        {getFieldDecorator('brand.owner', {})(
                            <Input placeholder="品牌权利人"/>
                        )}
                    </FormItem>
                  <FormItem
                      {...formItemLayout}
                      label={"品牌状态"}
                      hasFeedback
                  >
                      {getFieldDecorator('brand.brandStatus', {
                          rules: [
                              { required: true, message: '请选择品牌状态!' },
                          ],
                      })(
                          <Select placeholder="请选择品牌状态">
                              <Option value="5">国产保健食品</Option>
                              <Option value="6">进口保健食品</Option>

                          </Select>
                      )}
                  </FormItem>

                  <FormItem
                      {...formItemLayout}
                      label="商标注册证"
                  >
                      {getFieldDecorator('brand.registrationCertificate', {
                          valuePropName: 'fileList',
                          getValueFromEvent: this.normFile,
                      })(
                          <Upload name="uploadFile" action="/incc/file/upload/1/1"  beforeUpload={beforeUpload}
                                  onChange={this.handleChange} className="avatar-uploader">
                              {
                                  imageUrl ?
                                      <img src={imageUrl} alt="" className="avatar" /> :
                                      <Icon type="plus" className="avatar-uploader-trigger" />
                              }
                          </Upload>
                      )}
                  </FormItem>
              </div>


        )
    }
}
;
BrandForm = createForm()(BrandForm);
export default connect(
    state => {
        let {Brand} = state;
        return {
            Brand
        }
    }
)(BrandForm);