# spring-extension-bean

## Issue request of any form is welcome.

> 少一些套路,多一些真诚.
>
> Clean is good. Simple is cool.

## Quick start

### step 1 : 写个普通平凡的接口 (write a plain interface)

```java
public interface A {

  String sayHi(String name);

  Object giveMeSomething();

}
```

### step 2 : 写个普通平凡的实现 ( make a normal implementation )

```java
public class TestExtensionBeanADefault extends AbstractExtensionBeanA {

  @Resource
  private SomePlainBean somePlainBean;

  @Override
  public Object giveMeSomething() {
    return somePlainBean;
  }

}
```

### step 3 : 加个扩展点注解 ( add the @ExtensionBean annotation )

```java

@ExtensionBeans({
        @ExtensionBean(forInterface = A.class, forCase = Case.CASE_COMPOUND),
        @ExtensionBean(forInterface = B.class, forCase = Case.CASE_COMPOUND)
})
public class CompoundExtensionBean extends BaseBean implements A, B {

  @Resource
  private SomePlainBean somePlainBean;
  @Resource
  private A testExtensionBeanADefault;

  @Override
  public String sayHi(String name) {
    return saySomething(name);
  }

  @Override
  public Object giveMeSomething() {
    return somePlainBean;
  }

  @Override
  public String sayBye(String name) {
    return saySomething(name);
  }

  @Override
  public A ownsTheA() {
    return testExtensionBeanADefault;
  }
}
```

> JDK1.8 往后的版本, 可以写的更随意些
>
> Thanks to @Repeatable, we can make it even easier since JDK1.8.

```java
@ExtensionBean(forInterface = A.class, forCase = Case.CASE_COMPOUND),

@ExtensionBean(forInterface = B.class, forCase = Case.CASE_COMPOUND)
public class CompoundExtensionBean extends BaseBean implements A, B {
    ...
}
```

### step 4 : 开始玩耍吧 ( ready to play )

![spring-extension-bean-demo-gif](https://wx1.sinaimg.cn/large/60c4aab5gy1gsdgfjuc4kg20l60acnpt.gif)

### 最后别忘了@EnableExtensionBean ( Last but not least, don't forget the @EnableExtensionBean )

```java

@SpringBootApplication
@EnableExtensionBean(basePackages = {"space.pgg.spring.extension", "packages.in.your.project"})
public class TestApplication {
    ...
}
```

## 速查手册 (Quick Manual)

| 错误事项 | 内容 |
|---|---|
| Error Code | [CASE_NAME_NOT_CONFIGURED](#CASE_NAME_NOT_CONFIGURED) |
| Exception | space.pgg.spring.extension.exception.AnnotationConfigErrorException |
| 中文描述 | 扩展点配置错误 : 扩展点没有配置案例名称(case name)|
| 处理方式 | 检查扩展点实现类是否有配置相关 case name 的 `@ExtensionBean` 注解 |

---

| 错误事项 | 内容 |
|---|---|
| Error Code | [DUPLICATE_CASE_NAME](#DUPLICATE_CASE_NAME) |
| Exception | space.pgg.spring.extension.exception.AnnotationConfigErrorException |
| 中文描述 | 扩展点配置错误 : 案例名称(case name) 配置重复 |
| 处理方式 | 检查扩展点实现类 `@ExtensionBean` 的配置, 容器启动时找到多个相同 case name 的注解  |

---

| 错误事项 | 内容 |
|---|---|
| Error Code | [SCAN_BASE_PACKAGES_NOT_SET](#SCAN_BASE_PACKAGES_NOT_SET) |
| Exception | space.pgg.spring.extension.exception.AnnotationConfigErrorException |
| 中文描述 | 扩展点配置错误 : 注解 `@EnableExtension` 未配置 scanBasePackages |
| 处理方式 | 检查注解 `@EnableExtension` 中, scanBasePackages 的配置情况  |

---

| 错误事项 | 内容 |
|---|---|
| Error Code | [EXTENSION_BEAN_NOT_FOUND](#EXTENSION_BEAN_NOT_FOUND) |
| Exception | space.pgg.spring.extension.exception.ExtensionBeanNotFoundException |
| 中文描述 | 扩展点路由错误 : 未找到对应的扩展点 |
| 处理方式 | 此类问题是由于运行时无法找到对应的扩展点实现, 常见于如下两种情况 <br/> 1. 检查接口是否有对应 case name 的扩展点实现 <br/>  2. 如果有对应的扩展点实现, 再确认下是否在 `@EnableExtension` 的 scanBasePackages 范围内 |

---

| 错误事项 | 内容 |
|---|---|
| Error Code | [INTERFACE_CONSISTENCY_ERROR](#INTERFACE_CONSISTENCY_ERROR) |
| Exception | space.pgg.spring.extension.exception.AnnotationConfigErrorException |
| 中文描述 | 扩展点配置错误 : 扩展点接口一致性校验失败 |
| 处理方式 | 检查报错的扩展点是确有 继承/实现 `@ExtensionBean` 中指定的扩展点接口 |

---

| 错误事项 | 内容 |
|---|---|
| Error Code | [UNKNOWN_ERROR](#UNKNOWN_ERROR) |
| Exception | space.pgg.spring.extension.exception.UnknownException |
| 中文描述 | 未知错误 |
| 处理方式 | [提交ISSUE](http://gitlab.alibaba-inc.com/cnt-arch/spring-extension-bean/issues/new), 附上相关的问题描述 & 堆栈信息 |

## Change Log

- 0.1.0 记不清什么时候了 ( Sometime not sure )
  - 有了一个初步的想法就写了出来 ( came up with some vague thoughts and made it happen)

```
