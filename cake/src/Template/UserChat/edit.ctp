<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Form->postLink(
                __('Delete'),
                ['action' => 'delete', $userChat->chatID],
                ['confirm' => __('Are you sure you want to delete # {0}?', $userChat->chatID)]
            )
        ?></li>
        <li><?= $this->Html->link(__('List User Chat'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="userChat form large-9 medium-8 columns content">
    <?= $this->Form->create($userChat) ?>
    <fieldset>
        <legend><?= __('Edit User Chat') ?></legend>
        <?php
            echo $this->Form->input('userID1');
            echo $this->Form->input('userID2');
            echo $this->Form->input('message');
            echo $this->Form->input('fromUser');
            echo $this->Form->input('createDate');
            echo $this->Form->input('updateDate');
            echo $this->Form->input('delFlg');
            echo $this->Form->input('isBlock');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
