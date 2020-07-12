#第十四章 并发
## 14.1 线程
    线程：一个程序同时可以执行多个任务。每一个任务通常称为一个线程，它是线程控制的简称。
    
    
    进程：一个在内存中运行的应用程序。每个进程都有自己的独立的一块内存空间，一个进程可以有多个县城。
   ### 进程与线程的区别
    进程是资源分配的最小单位。县城是程序执行的最小单位。
    进程又自己的独立地址空间，没启动一个进程，系统会为他分配地址空间，建立数据表来维护代码段、堆栈段和数据段，这种操作非常昂贵。
    县城是共享进程中的数据的，使用相同的地址空间，因此cpu切换一个县城的话费远低于金成德花费。创建一个现成的开销也小于进程。
    县城之间的通信更方便，同意进程下的县城，共享全局变量、静态变量和数据，进程之间的通信需要一通信的方式进行。不过
    如何处理好同步呵护吃是编写多线程程序的难点。
    但是多进程程序更健壮，多线程程序只要有一个线程死掉，整个进程也死掉了，而一个进程死掉并不会对另外一个进程造成影响，因为进程有自己独立的地址空间。
    
   ### 进程与线程的资源 
    1堆与栈
        堆：　是大家共有的空间，分全局堆和局部堆。全局堆就是所有没有分配的空间，局部堆就是用户分配的空间。堆在操作系统
        对进程初始化的时候分配，运行过程中也可以向系统要额外的堆，但是记得用完了要还给操作系统，要不然就是内存泄漏。
        
        栈：是个线程独有的，保存其运行状态和局部自动变量的。栈在线程开始的时候初始化，每个线程的栈互相独立，因此，栈是　
        thread safe的。操作系统在切换线程的时候会自动的切换栈，就是切换　ＳＳ／ＥＳＰ寄存器。栈空间不需要在高级语言里面显
        式的分配和释放。
    其他
        线程共享的环境包括：进程代码段、进程的公有数据(利用这些共享的数据，线程很容易的实现相互之间的通讯)、
        进程打开的文件描述符、信号的处理器、进程的当前目录和进程用户ID与进程组ID。
        
        进程拥有这许多共性的同时，还拥有自己的个性。有了这些个性，线程才能实现并发性。这些个性包括：
        1.线程ID
        每个线程都有自己的线程ID，这个ID在本进程中是唯一的。进程用此来标
        识线程。
        2.寄存器组的值
        由于线程间是并发运行的，每个线程有自己不同的运行线索，当从一个线
        程切换到另一个线程上时，必须将原有的线程的寄存器集合的状态保存，以便
        将来该线程在被重新切换到时能得以恢复。
        3.线程的堆栈
        堆栈是保证线程独立运行所必须的。
        线程函数可以调用函数，而被调用函数中又是可以层层嵌套的，所以线程
        必须拥有自己的函数堆栈，使得函数调用可以正常执行，不受其他线程的影
        响。
        4.错误返回码
        由于同一个进程中有很多个线程在同时运行，可能某个线程进行系统调用
        后设置了errno值，而在该线程还没有处理这个错误，另外一个线程就在此时
        被调度器投入运行，这样错误值就有可能被修改。
        所以，不同的线程应该拥有自己的错误返回码变量。
        5.线程的信号屏蔽码
        由于每个线程所感兴趣的信号不同，所以线程的信号屏蔽码应该由线程自
        己管理。但所有的线程都共享同样的信号处理器。
        6.线程的优先级
        由于线程需要像进程那样能够被调度，那么就必须要有可供调度使用的参
        数，这个参数就是线程的优先级。
   ###进程与线程的同步
  进程：无名管道、有名管道、信号、共享内存、消息队列、信号量
   进程：互斥量、读写锁、自旋锁、线程信号、条件变量
   
   根本区别：进程是操作系统资源分配的基本单位，而线程是处理器任务调度和执行的基本单位
   
   资源开销：每个进程都有独立的代码和数据空间（程序上下文），程序之间的切换会有较大的开销；线程可以看做轻量级的进程，
   同一类线程共享代码和数据空间，每个线程都有自己独立的运行栈和程序计数器（PC），线程之间切换的开销小。
   
   包含关系：如果一个进程内有多个线程，则执行过程不是一条线的，而是多条线（线程）共同完成的；线程是进程的一部分，
   所以线程也被称为轻权进程或者轻量级进程。
   
   内存分配：同一进程的线程共享本进程的地址空间和资源，而进程之间的地址空间和资源是相互独立的
   
   影响关系：一个进程崩溃后，在保护模式下不会对其他进程产生影响，但是一个线程崩溃整个进程都死掉。所以多进程要比多线程健壮。
   
   执行过程：每个独立的进程有程序运行的入口、顺序执行序列和程序出口。但是线程不能独立执行，必须依存在应用程序中，
   由应用程序提供多个线程执行控制，两者均可并发执行
 
   
   
   #### 注意：
   1不要调用Thead类或Runnable对象的run方法。直接调用run方法，只会执行同一个现成的任务，而不是启动新线程。应该调用Theard。start
   方法，这个方法将创建一个执行润方法的新线程。
   ### java.lang.Thread 
   ####Thread （Runnable target）
   构造一个新的线程，用于调用给定的目标的run方法
   #### void start()
   启动这个线程，将引发调用run方法。这个方法将立即返回，并且新县城将并发运行。
   #### void run（）
   调用关联的Runnable的run（）方法
   ### java.lang.Runnable
  #### void run*()
  必须覆盖这个方法，并在这个方法中提供索要执行的任务指令
  ## 14.2 中断线程
  当线程的run 方法执行方法体中最后一条语句后， 并经由执行return 语句返冋时， 或者出现了在方法中没有捕获的异常时， 线程将终止。
  在Java 的早期版本中， 还有一个stop 方法， 其他线程可以调用它终止线程。但是， 这个方法现在已经被弃用了。
   然而， interrupt 方法可以用来请求终止线程。
   当对一个线程调用interrupt 方法时，线程的中断状态将被置位。这是每一个线程都具有
   的boolean 标志。每个线程都应该不时地检査这个标志， 以判断线程是否被中断。
   
   要想弄清中断状态是否被置位， 首先调用静态的Thread.currentThread 方法获得当前线程， 然后调用islnterrupted 方法：
   
       while (!Thread.currentThread().islnterrupted() && more work to do)
       {
       do more work
       }
       
  如果线程被阻塞， 就无法检测中断状态。这是产生InterruptedExceptioii 异常的地方。当在一个被阻塞的线程（调用sleep 或wait ) 
  上调用interrupt 方法时， 阻塞调用将会被Interrupted Exception 异常中断。（存在不能被中断的阻塞I/O 调用，
   应该考虑选择可中断的调用
   
   没有任何语言方面的需求要求一个被中断的线程应该终止。中断一个线程不过是引起它
   的注意。被中断的线程可以决定如何响应中断。某些线程是如此重要以至于应该处理完异常
   后， 继续执行， 而不理会中断。但是，更普遍的情况是，线程将简单地将中断作为一个终止
   的请求。这种线程的run 方法具有如下形式：
   
           Runnable r = () -> {
               try{
               while (!Thread.currentThread() .islnterrupted0 && more work to do){
                 do more work
                 }
               }catch(InterruptedException e){
               // thread was interr叩ted during sleep or wait
               }
               finally{
                cleanup, if required
               }// exiting the run method terminates the thread
           }；
   
   如果在每次工作迭代之后都调用sleep 方法（或者其他的可中断方法)，islnterrupted 检测
   既没有必要也没有用处。如果在中断状态被置位时调用sleep 方法， 它不会休眠。相反，它
   将清除这一状态（丨）并拋出IntemiptedException。因此， 如果你的循环调用sleep， 不会检
   测中断状态。相反，要如下所示捕获InterruptedException 异常：
   
       Runnable r = 0 -> {
       try
       {
       while { more work to do)
       {
       do more work
       Thread,sleep(delay);
       }
       }
       catch(InterruptedException e)
       {
       // thread was interrupted during sleep
       }
       finally
       {
       cleanup, if required
       }
       //exiting the run method terminates the thread
       }；
   
   有两个非常类似的方法，interrupted 和islnterrupted。Interrupted 方法是一个静态
   方法， 它检测当前的线程是否被中断。而且， 调用interrupted 方法会清除该线程的中断
   状态。另一方面， islnterrupted 方法是一个实例方法， 可用来检验是否有线程被中断。调
   用这个方法不会改变中断状态。
   
   
   
   ### java。lang.Thread
   #### void interrupt()
   向线程发送中断请求。线程的中断状态将被设置为true。如果目前该线程被一个sleep
   调用阻塞，那么， InterruptedException 异常被抛出。
   
   ##### static boolean interrupted()
   测试当前线程（即正在执行这一命令的线程）是否被中断。注意，这是一个静态方法。
   这一调用会产生副作用—它将当前线程的中断状态重置为false。
   
   ####boolean islnterrupted()
   测试线程是否被终止。不像静态的中断方法，这一调用不改变线程的中断状态。
   
   #### static Thread currentThread()
   返回代表当前执行线程的Thread 对象。
   
   ## 14.3 线程状态
   线程可以有如下6 种状态：
   要确定一个线程的当前状态， 可调用getState 方法。
   
   •New ( 新创建）
   ### 14.3.1 新创建线程
   当用new 操作符创建一个新线程时， 如newThread(r)， 该线程还没有开始运行。这意味
   着它的状态是new。当一个线程处于新创建状态时， 程序还没有开始运行线程中的代码。在
   线程运行之前还有一些基础工作要做。
   
   
   •Runnable (可运行） 
   

   
   ###   14.3.2 可运行线程
   一旦调用start 方法，线程处于runnable 状态。一个可运行的线桿可能正在运行也可能没
   有运行， 这取决于操作系统给线程提供运行的时间。（Java 的规范说明没有将它作为一个单独
   状态。一个正在运行中的线程仍然处于可运行状态。）
   
   一旦一个线程开始运行，它不必始终保持运行。事实上，运行中的线程被中断，目的是
   为了让其他线程获得运行机会。线程调度的细节依赖于操作系统提供的服务。抢占式调度系
   统给每一个可运行线程一个时间片来执行任务。当时间片用完， 操作系统剥夺该线程的运行
   权， 并给另一个线程运行机会（见图14-4 )。当选择下一个线程时， 操作系统考虑线程的优
   先级。
   
   在具有多个处理器的机器上， 每一个处理器运行一个线程， 可以有多个线程并行运行。
   当然， 如果线程的数目多于处理器的数目， 调度器依然采用时间片机制。
   记住，在任何给定时刻，二个可运行的线程可能正在运行也可能没有运行（这就是为什
   么将这个状态称为可运行而不是运行)。

   •Blocked ( 被阻塞）
   
   ### 14.3.3 被阻塞线程和等待线程
   当线程处于被阻塞或等待状态时， 它暂时不活动。它不运行任何代码且消耗最少的资
    源。直到线程调度器重新激活它。细节取决于它是怎样达到非活动状态的。
   被阻塞线程和等待线程
   
   •当一个线程试图获取一个内部的对象锁（而不是javiutiUoncurrent 库中的锁)，
   而该
   锁被其他线程持有， 则该线程进人阻塞状态（我们在14.5.3 节讨论java.util.concurrent
   锁，在14.5.5 节讨论内部对象锁)。 当所有其他线程释放该锁，并且线程调度器允许
   本线程持有它的时候，该线程将变成非阻塞状态。
   
   ■ 当线程等待另一个线程通知调度器一个条件时， 它自己进入等待状态。我们在第
   14.5.4 节来讨论条件。在调用Object.wait 方法或Thread.join 方法， 或者是等待java,
   util.concurrent 库中的Lock 或Condition 时， 就会出现这种情况。实际上，被阻塞状态
   与等待状态是有很大不同的。
   
   •有几个方法有一个超时参数。调用它们导致线程进人计时等待（ timed waiting ) 状
   态。这一状态将一直保持到超时期满或者接收到适当的通知。带有超时参数的方法有
   Thread.sleep 和Object.wait、Thread.join、Lock,tryLock 以及Condition.await 的计时版。
   
   。当一个线
   程被阻塞或等待时（或终止时)，
   另一个线程被调度为运行状态。当一个线程被重新激活（例
   如， 因为超时期满或成功地获得了一个锁)，
   调度器检查它是否具有比当前运行线程更高的优
   先级。如果是这样，调度器从当前运行线程中挑选一个， 剥夺其运行权，选择一个新的线程
   运行。
   
   
   •Waiting ( 等待）
   
   ### 14.3.4 被终止的线程
   线程因如下两个原因之一而被终止：
   •因为run 方法正常退出而自然死亡。
   第1 4 章并友6 3 7
   •因为一个没有捕获的异常终止了nm 方法而意外死亡。
   特别是， 可以调用线程的stop 方法杀死一个线程。该方法抛出ThreadDeath 错误对象,
   由此杀死线程。但是，stop 方法已过时， 不要在自己的代码中调用这个方法。
   
   ### java.lang.Thread()
   #### •void join( )
   等待终止指定的线程。
   #### v o i d j o i n( l o n g mi 1 1 i s )
   等待指定的线程死亡或者经过指定的毫秒数。
   
   •T h r e a d.S t a t e g e t S t a t eO 5 . 0
   得到这一线程的状态；NEW、RUNNABLE BLOCKED、WAITING HMED_WAmNG
   或TERMINATED 之一。
   #### •v o i d s t o p( )
   停止该线程。这一方法已过时。
   #### void suspend()
   暂停这一线程的执行。这一方法已过时。
   #### void resume()
   恢复线程。这一方法仅仅在调用suspendO 之后调用。这一方法已过时。
   
   •Timed waiting (计时等待）
   
   •Terminated ( 被终止）
   
   ## 14.4 线程属性
   线程优先级、守护线程、线程组以及处理未捕获异常的处理器。
   
   ### 14.4.1 线程优先级
   在Java 程序设计语言中， 每一个线程有一个优先级。默认情况下， 一+线程继承它的父
   线程的优先级。可以用setPriority 方法提高或降低任何一个线程的优先级。可以将优先级设
   置为在MIN_PRIORITY (在Thread 类中定义为1 ) 与MAX_PRIORITY (定义为10 ) 之间的
   任何值。NORM_PRIORITY 被定义为5。
   
  例如， Windows 有7 个优先级别。一些Java 优先级将映射到相同的操作系统优先级。在
  Oracle 为Linux 提供的Java 虚拟机中， 线程的优先级被忽略一所有线程具有相同的优先级。
  初级程序员常常过度使用线程优先级。为优先级而烦恼是事出有因的。不要将程序构建
  为功能的正确性依赖于优先级。
   
   
   #### •void setPriority(int newPriority)
   设置线程的优先级。优先级必须在Thread.MIN_PRIORITY 与Thread.MAX_PRIORITY
   之间。一般使用Thread.NORMJ»RIORITY 优先级。
   ####•static int MIN_PRIORITY
   线程的最小优先级。最小优先级的值为1。
  #### •static int N0RM_PRI0RITY
   线程的默认优先级。默认优先级为5。

  #### •static int MAX—PRIORITY
   线程的最高优先级。最高优先级的值为10。
   #### •static void yield( )
   导致当前执行线程处于让步状态。如果有其他的可运行线程具有至少与此线程同样高
   的优先级，那么这些线程接下来会被调度。注意，这是一个静态方法。
   
   ### 14.4.2守护线程
   可以通过调用
   t .setDaemon(true) ;
   
   
   将线程转换为守护线程（daemon thread)。 这样一个线程没有什么神奇。守护线程的唯一用途
   是为其他线程提供服务
   
   守护线程有时会被初学者错误地使用， 他们不打算考虑关机（ shutdown) 动作。但是，
   这是很危险的。守护线程应该永远不去访问固有资源， 如文件、数据库，因为它会在任何时
   候甚至在一个操作的中间发生中断。
   
   ####•void setDaemon( boolean isDaemon )
   标识该线程为守护线程或用户线程。这一方法必须在线程启动之前调用。
   
   ### 1 4 . 4.3 未捕获异常处理器
   线程的run 方法不能抛出任何受查异常， 但是，非受査异常会导致线程终止。在这种情
   况下，线程就死亡了。
   但是，不需要任何catch 子句来处理可以被传播的异常。相反， 就在线程死亡之前， 异
   常被传递到一个用于未捕获异常的处理器。
   该处理器必须属于一个实现Thread.UncaughtExceptionHandler 接口的类。这个接口只有—个方法。
   
   void uncaughtException(Thread t , Throwable e)
   
   可以用setUncaughtExceptionHandler 方法为任何线程安装一个处理器。也可以用Thread
   类的静态方法setDefaultUncaughtExceptionHandler 为所有线程安装一个默认的处理器。替换
   处理器可以使用日志API 发送未捕获异常的报告到日志文件。
   如果不安装默认的处理器， 默认的处理器为空。但是， 如果不为独立的线程安装处理
   器，此时的处理器就是该线程的ThreadGroup 对象。
  
  ThreadGroup 类实现Thread.UncaughtExceptionHandler 接口。它的uncaughtException 方
  法做如下操作：
  
  1 ) 如果该线程组有父线程组， 那么父线程组的uncaughtException 方法被调用。
  
  2 ) 否则， 如果Thread.getDefaultExceptionHandler 方法返回一个非空的处理器， 则调用
  该处理器。
  
  3 ) 否则， 如果Throwable 是ThreadDeath 的一个实例， 什么都不做。
  
  4 ) 否则， 线程的名字以及Throwable 的栈轨迹被输出到System.err 上。
  这是你在程序中肯定看到过许多次的栈轨迹。
   
   •static void setDefaultUncaughtExceptionHandler(Thread.UncaughtException
   Handler handler ) 5.0
   
   •static Thread.UncaughtExceptionHandler getDefaultUncaughtException
   HandlerO 5.0
   
   设置或获取未捕获异常的默认处理器。
   
   •void setUncaughtExceptionHandler( Thread •UncaughtExceptionHandler
   handler ) 5.0
   
   •Thread.UncaughtExceptionHandler getUncaughtExceptionHandler( ) 5.0
   
   设置或获取未捕获异常的处理器。如果没有安装处理器， 则将线程组对象作为处理器。
   
   
   •void UncaughtException(Thread t , Throwable e)
   
   当一个线程因未捕获异常而终止， 按规定要将客户报告记录到日志中。
   
   参数：t 由于未捕获异常而终止的线程
   
   e 未捕获的异常对象
   
   •void UncaughtException( Thread t, Throwable e)
   
   如果有父线程组， 调用父线程组的这一方法; 或者， 如果Thread 类有默认处理器，调
   用该处理器， 否则， 输出栈轨迹到标准错误流上（但是， 如果e 是一个ThreadDeath
   对象， 栈轨迹是被禁用的。ThreadDeath 对象由stop 方法产生， 而该方法已经过时)。
   
# 第十五章 javase8的流库
## 15.1 从迭代器到流操作
处理集合时，我们通常会迭代遍历他的元素，并在元素上执行某项操作。
    
        String contents = 呢我String（Files.readAllBytes(
            Paths.get("alice.txt")),StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("\PL+"));
            //迭代他
            Long Count = 0;
            for(String w ： words){
                if(w.length()>12){
                    count++;
                }
            }
        使用流时
        
        Long count = words.stream()
        .filter(w->w.length()>12)
        .count();
        
        
 流不存储其元素，这些元素可能存储在底层的集合中，或者按需生成。
 
 流的操作不会修改其数据源。fiter（）方法不会从新的流中移除元素，而是会生成新的刘，其中不包括被过滤掉的流。
 
 流的操作是惰性执行的，这意味着需要结果时，操作才会执行。
 
 工作流创建流的典型过程：
 
 1创建一个流
 
 2制定将初始化流转换为其他流的中间操作，可能包含多个步骤
 
 3应用种植操作，草儿产生结果。这个操作会强制执行之前的惰性操作。从此以后这个刘就再也不能用了。
 
 
 
 
 
 