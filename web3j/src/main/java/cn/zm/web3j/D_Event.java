package cn.zm.web3j;

import io.reactivex.disposables.Disposable;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.Arrays;

public class D_Event {
    public static void main(String[] args) throws IOException {
        // 定义 Transfer Event 告诉 Web3j 我要监听 ERC20 Transfer Event
        Event transferEvent = new Event(
                "Transfer",
                // true 的含义（非常关键） new TypeReference<Address>(true)
                // 这个： true = indexed 对应 Solidity： address indexed from
                // 如果写错会怎样？ 后面解析 Topics 时： 全部错位 这是 Web3j 新手高频错误。
                Arrays.asList(
                        new TypeReference<Address>(true) {},
                        new TypeReference<Address>(true) {},
                        new TypeReference<Uint256>() {}
                )
        );

        // 五、生成 Event Topic

        String topic = EventEncoder.encode(transferEvent);
        System.out.println(topic);

        // # 这是什么？输出类似：0xddf252ad... 这是：Event Signature Hash

    }
}
