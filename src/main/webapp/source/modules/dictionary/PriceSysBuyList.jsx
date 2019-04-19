import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshDictionaryList2, changeDictionaryStatus, delDictionary} from 'actions/DictionaryActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';

const confirm = Modal.confirm;
/**
 * Dictionary: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class PriceSysBuyList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
        this.props.onView(id);
    }

    edit(id){
        console.log(" List2 修改 获取ID********************");
        console.log(id);
        this.props.onEdit(id);
    }

    delete(id, dictionaryCriteria, pageNum){
        this.props.dispatch(delDictionary(id, dictionaryCriteria));
    }

    render() {

        const columns = [
            {title: '产品编号', dataIndex: '', key: 'id', render: (text, record, index) => (

                <span>
                    {index + 1}
                </span>

            )},
            {title: '名称', dataIndex: 'name', key: 'name'},
            {title: '描述说明', dataIndex: 'describe', key: 'describe'},
            {title: '价格（元）', dataIndex: 'price', key: 'price'},
            {title: '个数', dataIndex: 'total', key: 'total'},
            {title: '起始时间', dataIndex: 'createTime', key: 'createTime'},
            {title: '截止时间', dataIndex: 'endTime', key: 'endTime'},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (



        <span>
               {industrys.get(record.id)}

              {/*  if(record.id=='1'){
                    <a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>
                }else{
                    <a href={'/incc/alipay/goConfirm.action?productId='+record.id} target ="_blank" style={{color:'#2db7f5',cursor:'pointer'}}> 购买</a>
                }*/}

                  {/*
                   {<a href={'/incc/alipay/goConfirm.action?productId='+record.id} target ="_blank" style={{color:'#2db7f5',cursor:'pointer'}}> 购买</a>}
                   {<a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>}
                  */}

                   {/* {<a onClick={()=>this.edit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 修改</a>}*/}
                  {/*<DelConfirm onOk = {()=>this.delete(record.id, dictionaryCriteria, dictionaryList2.pageNum)}/>*/}
                </span>

            ),
            },
        ];
         
        const {dictionaryList2} = this.props;
        const {dictionaryCriteria} = this.props;
      

        let industrys = new Map();

        dictionaryList2.forEach((item)=>{
            if(item.name != "首次加入费"){
                industrys.set(item.id,<a href={'/incc/alipay/goConfirm.action?productId='+item.id} target ="_blank" style={{color:'#2db7f5',cursor:'pointer'}}> 购买</a>);
            }
        })


        console.log('***************************');
        console.log(industrys);
        console.log('***************************!!!!!!!!!!!!!!!!!!!!!');
        return (

            <div>

                <div>

                    <Table
                        columns={columns}
                        bordered
                        dataSource={dictionaryList2}
                        className="table"
                    />

                </div>

            </div>
        )
    }
};
